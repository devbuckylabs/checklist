package com.buckylabs.checklist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

public class Add_Category extends AppCompatActivity {

    private Context context;
    private EditText editText_addCat;
    private ImageButton imageButton_addCat;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category);
        context = this;
        databaseHelper = new DatabaseHelper(context);
        editText_addCat = findViewById(R.id.edit_text_addCat);
        imageButton_addCat = findViewById(R.id.button_addCat);

        imageButton_addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_addCat.getText().length() > 0) {
                    String cat_name = editText_addCat.getText().toString();
                    Category category = new Category(getid(), cat_name, null);
                    editText_addCat.getText().clear();
                    boolean res = databaseHelper.insertData(category);
                    if (res) {

                        Toast.makeText(context, cat_name + " is added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public int getid() {
        Cursor cursor = databaseHelper.getdbdata();
        cursor.moveToLast();
        int id = cursor.getInt(0);
        return ++id;
    }

}
