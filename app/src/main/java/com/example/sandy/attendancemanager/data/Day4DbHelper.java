package com.example.sandy.attendancemanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sandy.attendancemanager.data.AttendanceContract.ThursdayEntry;

import java.util.ArrayList;

/**
 * Created by sandy on 29-06-2017.
 */

public class Day4DbHelper extends SQLiteOpenHelper {

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "attendance.db";


    public Day4DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public ArrayList<String> getSubjectsFromDb(){
        ArrayList<String> subs = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String QUERY_GET_SUBJECTS = "SELECT "+ThursdayEntry.COLUMN_SUBJECT_NAME+
                " FROM " +ThursdayEntry.TABLE_NAME;
        Cursor c = db.rawQuery(QUERY_GET_SUBJECTS,null);

        while(c.moveToNext()){
            int nameColumnIndex = c.getColumnIndex(ThursdayEntry.COLUMN_SUBJECT_NAME);
            subs.add(c.getString(nameColumnIndex).trim().toUpperCase());
        }
        db.close();
        c.close();
        return subs;
    }

    public void addSubjectToDb(String name){
        ContentValues values = new ContentValues();
        values.put(ThursdayEntry.COLUMN_SUBJECT_NAME,name);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(ThursdayEntry.TABLE_NAME,null,values);
        db.close();
        values.clear();
    }

    public void deleteSubjectInDb(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + ThursdayEntry.TABLE_NAME + " WHERE "
                + ThursdayEntry.COLUMN_SUBJECT_NAME + "=\"" + name +"\";" );
        db.close();
    }

}