package com.buckylabs.checklist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
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
    private List<Category> categories = new ArrayList<>();
    private Context context;

    public Category_Adapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.category, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Adapter.ViewHolder viewHolder, int i) {
        final Category categoryListItem = categories.get(i);
        viewHolder.textView.setText(categoryListItem.getCategory_name());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, categoryListItem.getCategory_name(), Toast.LENGTH_SHORT).show();
                // Log.e("List", categoryListItem.getListItems().toString());
                //Toast.makeText(context, categoryListItem.getListItems().toString(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, SecondActivity.class);
                i.putExtra("ID", categoryListItem.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cat_textView);
        }
    }
}
