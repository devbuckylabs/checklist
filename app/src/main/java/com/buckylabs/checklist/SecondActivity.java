package com.buckylabs.checklist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private Context context = this;
    private List<ListItem> listItems = new ArrayList<>();
    private RecyclerView listitem_recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listitem_recyclerView = findViewById(R.id.item_recyclerview);
        listitem_recyclerView.hasFixedSize();
        listitem_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListItem_Adapter(listItems, context);
        listitem_recyclerView.setAdapter(adapter);
        listItems.addAll((List<ListItem>) getIntent().getSerializableExtra("intentlist"));

        Log.e("Listoo", listItems.toString());
        adapter.notifyDataSetChanged();


    }
}
