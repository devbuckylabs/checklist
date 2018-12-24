package com.buckylabs.checklist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private Context context = this;
    private List<ListItem> listItems = new ArrayList<>();
    private RecyclerView listitem_recyclerView;
    private RecyclerView.Adapter adapter;
    private EditText editTextAddListItem;
    private ImageButton imageButton;
    private DatabaseHelper databaseHelper;
    private TextView textView_catName;
    private CheckBox checkBox_selectAll;
    private ImageButton imageButton_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listitem_recyclerView = findViewById(R.id.item_recyclerview);
        listitem_recyclerView.hasFixedSize();
        listitem_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListItem_Adapter(listItems, context);
        listitem_recyclerView.setAdapter(adapter);
        editTextAddListItem = findViewById(R.id.editText_add_list_item);
        imageButton = findViewById(R.id.imgbutton);

        textView_catName = findViewById(R.id.textView_Catname);
        checkBox_selectAll = findViewById(R.id.checkBox_selectAll);
        imageButton_delete = findViewById(R.id.imgbutton_delete);


        databaseHelper = new DatabaseHelper(context);
        populate_recyclerView();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextAddListItem.getText().length() > 0) {

                    ListItem listItem = new ListItem(editTextAddListItem.getText().toString(), false);
                    listItems.add(listItem);
                    int id = getIntent().getExtras().getInt("ID");
                    Category category = databaseHelper.getCategory(id);
                    Log.e("cat", category.toString());
                    category.setListItems(listItems);
                    Log.e("cat added DB", category.toString());
                    editTextAddListItem.getText().clear();
                    adapter.notifyDataSetChanged();
                    addListItemtoDb(category);
                }

            }
        });


        textView_catName.setText(getIntent().getExtras().getString("CategoryName"));

        checkBox_selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (listItems.size() == 0) {
                    checkBox_selectAll.setChecked(false);
                    Toast.makeText(context, "No items to select", Toast.LENGTH_SHORT).show();
                } else {
                    checkBox_selectAll.setChecked((isChecked));

                    Log.e("List", "in");
                    if (isChecked) {

                        for (ListItem listItem : listItems) {
                            listItem.setItemChecked(true);
                        }

                        adapter.notifyDataSetChanged();

                    }
                }

            }
        });


        imageButton_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listItems != null) {
                    List<ListItem> listItemsToUpdate = new ArrayList<>();
                    for (ListItem listItem : listItems) {

                        if (listItem.isItemChecked()) {

                        } else {
                            listItemsToUpdate.add(listItem);
                        }

                    }
                    listItems.clear();
                    listItems.addAll(listItemsToUpdate);
                    int id = getIntent().getExtras().getInt("ID");
                    Category category = databaseHelper.getCategory(id);
                    Log.e("BeforeList", listItemsToUpdate.toString());
                    category.setListItems(listItems);
                    Log.e("AfterList", listItemsToUpdate.toString());
                    Log.e("CatList", category.getListItems().toString());
                    addListItemtoDb(category);

                }
                adapter.notifyDataSetChanged();
                checkBox_selectAll.setChecked(false);

            }
        });

    }


    public void addListItemtoDb(Category category) {
        databaseHelper.updatedbdata(category);
    }

    public void populate_recyclerView() {

        int id = getIntent().getExtras().getInt("ID");
        Category category = databaseHelper.getCategory(id);
        if (category.getListItems() != null) {
            listItems.addAll(category.getListItems());
        }
        adapter.notifyDataSetChanged();
    }

    public List<ListItem> getlistItems(int id) {
        Category category = databaseHelper.getCategory(id);
        return category.getListItems();

    }
}

