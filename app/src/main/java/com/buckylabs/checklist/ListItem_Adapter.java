package com.buckylabs.checklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListItem_Adapter extends RecyclerView.Adapter<ListItem_Adapter.ViewHolder> {

    private List<ListItem> itemList;
    private Context context;

    public ListItem_Adapter(List<ListItem> listItems, Context context) {
        this.context = context;
        this.itemList = listItems;
    }

    @NonNull
    @Override
    public ListItem_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ListItem listItem = itemList.get(i);
        Log.e("Bind", listItem.toString());
        viewHolder.textView.setText(listItem.getItem());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listitem_textView);
        }
    }
}
