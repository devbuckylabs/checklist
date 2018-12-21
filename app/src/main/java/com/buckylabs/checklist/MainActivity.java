package com.buckylabs.checklist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context = this;
    private RecyclerView cat_recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Category> categories = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(context, "OnCreate", Toast.LENGTH_SHORT).show();

        cat_recyclerView = findViewById(R.id.cat_recycler_view);
        cat_recyclerView.hasFixedSize();
        cat_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Category_Adapter(categories, context);
        cat_recyclerView.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(context);


        populate_recyclerView();


/*

        if (true) {
            boolean result = databaseHelper.insertData("Med KIt");
            if (result) {
                Log.e("Success", " " + result);
            } else {
                Log.e("Failed", " " + result);

            }

        }
*/


        /*getdata();
        updatedata();
*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Add_Category.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

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


    public void getdata() {
        Cursor res = databaseHelper.getdbdata();
        while (res.moveToNext()) {
            Log.e("Db ", res.getString(1));
            Gson gson = new Gson();
            Category category = gson.fromJson(res.getString(1), Category.class);

            Log.e("cat", category.toString());
        }

    }

    public void updatedata() {

     /*   boolean res = databaseHelper.updatedbdata(3, "WaterMelons");
        if (res) {
            Cursor cur = databaseHelper.getdbdata();
            while (cur.moveToNext()) {
                Log.e("Update", cur.getString(1));
            }
        }*/

    }

    public void populate_recyclerView() {

        Cursor cur = databaseHelper.getdbdata();
        Gson gson = new Gson();
        Log.e("Pop", "Im in");
        cur.moveToFirst();
        Log.e("Cur Pos", " " + cur.getPosition());

        if (cur != null) {
            if (cur.moveToFirst())
                do {
                    Log.e("Cur Pos While", " " + cur.getPosition());

                    int id = cur.getInt(0);
                    String column_Name = cur.getString(1);
                    Type type = new TypeToken<List<ListItem>>() {
                    }.getType();
                    List<ListItem> listItems = gson.fromJson(cur.getString(2), type);
                    Category category = new Category(id, column_Name, listItems);
                    categories.add(category);
                    Log.e("catogories", categories.toString());
                    Log.e("populate_RV", "Adding " + category);


                } while (cur.moveToNext());

        }


    }


}