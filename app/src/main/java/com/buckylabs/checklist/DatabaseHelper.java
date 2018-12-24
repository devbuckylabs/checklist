package com.buckylabs.checklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Category.db";
    public static final String TABLE_NAME = "Category_Table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "Category_Name";
    public static final String Column_3 = "List_Items";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY ,Category_Name TEXT, List_Items Text)");
        Log.e("Oncreate", "DB created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Category category) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Gson gson = new Gson();
        String json = gson.toJson(category.getListItems(), Category.class);
        contentValues.put(COLUMN_1, category.getId());
        contentValues.put(COLUMN_2, category.getCategory_name());
        contentValues.put(Column_3, json);
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


    public void reset_table() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public boolean updatedbdata(Category category) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Gson gson = new Gson();
        Type type = new TypeToken<List<ListItem>>() {
        }.getType();
        String json = gson.toJson(category.getListItems(), type);

        contentValues.put(Column_3, json);
        int row = db.update(TABLE_NAME, contentValues, String.format("%s = ?", "ID"), new String[]{Integer.toString(category.getId())});
        Log.e("Row", row + " is affected");
        return true;
    }

   /* public boolean updateListItem(ListItem listItem){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        Gson gson=new Gson();
        String json=gson.toJson()

    }
    */

    public void droptable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public Category getCategory(int id) {
        Cursor cur = getdbdata();
        Gson gson = new Gson();
        Log.e("Im", "going in");
        if (cur != null) {
            Log.e("Im", "not null");
            if (cur.moveToFirst())
                do {
                    Log.e("Im", "inn");
                    if (id == cur.getInt(0)) {
                        Log.e("Im", "innnn");
                        String column_Name = cur.getString(1);
                        Type type = new TypeToken<List<ListItem>>() {
                        }.getType();
                        List<ListItem> listItems = gson.fromJson(cur.getString(2), type);
                        Category category = new Category(id, column_Name, listItems);
                        return category;
                    }

                } while (cur.moveToNext());

        }
        return null;
    }


}
