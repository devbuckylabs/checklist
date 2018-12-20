package com.buckylabs.checklist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context = this;
    private RecyclerView cat_recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CategoryListItem> categoryListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cat_recyclerView = findViewById(R.id.cat_recycler_view);
        cat_recyclerView.hasFixedSize();
        cat_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Category_Adapter(categoryListItems, context);
        cat_recyclerView.setAdapter(adapter);

        List<ListItem> dummyshoplist = new ArrayList<>();
        dummyshoplist.add(new ListItem("Pen"));
        dummyshoplist.add(new ListItem("Paper"));

        List<ListItem> dummysingers = new ArrayList<>();
        dummysingers.add(new ListItem("A R Rehman"));
        dummysingers.add(new ListItem("Eminem"));

        categoryListItems.add(new CategoryListItem("Shopping List", dummyshoplist));
        categoryListItems.add(new CategoryListItem("Singers", dummysingers));

        adapter.notifyDataSetChanged();



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
