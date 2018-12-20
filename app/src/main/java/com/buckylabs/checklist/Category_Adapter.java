package com.buckylabs.checklist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {
    private List<CategoryListItem> categoryListItems = new ArrayList<>();
    private Context context;

    public Category_Adapter(List<CategoryListItem> categoryListItems, Context context) {
        this.categoryListItems = categoryListItems;
        this.context = context;
    }

    @NonNull
    @Override
    public Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.category_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Adapter.ViewHolder viewHolder, int i) {
        final CategoryListItem categoryListItem = categoryListItems.get(i);
        viewHolder.textView.setText(categoryListItem.getCategory_name());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, categoryListItem.getCategory_name(), Toast.LENGTH_SHORT).show();
                Log.e("List", categoryListItem.getListItems().toString());
                Toast.makeText(context, categoryListItem.getListItems().toString(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, SecondActivity.class);
                i.putExtra("intentlist", (Serializable) categoryListItem.getListItems());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryListItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cat_textView);
        }
    }
}
