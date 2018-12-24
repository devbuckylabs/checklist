package com.buckylabs.checklist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListItem_Adapter extends RecyclerView.Adapter<ListItem_Adapter.ViewHolder> {

    private List<ListItem> itemList;
    private Context context;
    private DatabaseHelper databaseHelper;
    private List<ListItem> updateListItems = new ArrayList<>();

    public ListItem_Adapter(List<ListItem> listItems, Context context) {
        this.context = context;
        this.itemList = listItems;
    }

    @NonNull
    @Override
    public ListItem_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        databaseHelper = new DatabaseHelper(context);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final ListItem listItem = itemList.get(i);

        viewHolder.textView.setText(listItem.getItem());
        viewHolder.checkBox.setChecked(listItem.isItemChecked());
        Log.e("Bind", listItem.toString());
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewHolder.checkBox.setChecked((isChecked));
                Log.e("onCheckChanged", viewHolder.checkBox.isChecked() + " ");
                listItem.setItemChecked(viewHolder.checkBox.isChecked());
                updateListItems.clear();
                updateListItems.addAll(itemList);
                Log.e("onCheckChanged", listItem.toString());
                int id = ((Activity) context).getIntent().getExtras().getInt("ID");
                Log.e("ID", id + "");
                viewHolder.addListItemToDB(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listitem_textView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        public void addListItemToDB(int id) {
            Category category = databaseHelper.getCategory(id);
            category.setListItems(updateListItems);
            boolean res = databaseHelper.updatedbdata(category);
            if (res) {
                Log.e("update", "row update complete");
            } else {
                Log.e("update", "row update failed");

            }

        }
    }
}
