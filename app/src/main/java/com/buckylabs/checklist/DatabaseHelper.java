package com.buckylabs.checklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Category.db";
    public static final String TABLE_NAME = "Category_Table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "Category_Name";
    public static final String Column_ONE = "List_Items";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Category_Name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String category) {

        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 2, 3);
        ContentValues contentValues = new ContentValues();
        Gson gson = new Gson();
        String json = gson.toJson(category, Category.class);
        contentValues.put(COLUMN_2, json);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        return true;

    }

    public Cursor getdbdata() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * From " + TABLE_NAME, null);
        return res;
    }


    public boolean updatedbdata(int id, String category_name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Category cat = new Category(category_name);
        Gson gson = new Gson();
        String json = gson.toJson(cat, Category.class);
        contentValues.put(COLUMN_2, json);
        int row = db.update(TABLE_NAME, contentValues, String.format("%s = ?", "ID"), new String[]{Integer.toString(1)});
        Log.e("Row", row + " is affected");
        return true;
    }
}
