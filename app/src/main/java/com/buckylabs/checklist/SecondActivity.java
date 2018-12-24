package com.buckylabs.checklist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

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



}

