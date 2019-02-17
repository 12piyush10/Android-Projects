package com.piyush_pooja.fragement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DataBase_Name= "Student.db";
    String Table_Name= "student_table";
    String col_1= "ID";
    String col_2= "Name";
    String col_3= "Surname";
    String col_4= "Marks";

    public DataBaseHelper(Context context) {
        super(context,DataBase_Name, null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table"+ Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+Table_Name);
        onCreate(db);

    }
}
