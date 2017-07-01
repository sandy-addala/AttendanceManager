package com.example.sandy.attendancemanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sandy.attendancemanager.data.AttendanceContract.*;

import java.util.ArrayList;

/**
 * Created by sandy on 29-06-2017.
 */

public class SubjectsDbHelper extends SQLiteOpenHelper {

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "attendance.db";


    public SubjectsDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Main table
        String QUERY_CREATE_SUBJECT_TABLE  = "CREATE TABLE " + SubjectsEntry.TABLE_NAME + " ("
                + SubjectsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SubjectsEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL, "
                + SubjectsEntry.COLUMN_TOTAL_CLASSES + " INTEGER NOT NULL DEFAULT 0, "
                + SubjectsEntry.COLUMN_PRESENT_CLASSES + " INTEGER NOT NULL DEFAULT 0); ";
        //Monday table
        String QUERY_CREATE_MONDAY_TABLE = "CREATE TABLE " + MondayEntry.TABLE_NAME + " ("
                + MondayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MondayEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL); ";
        //Tuesday table
        String QUERY_CREATE_TUESDAY_TABLE = "CREATE TABLE " + TuesdayEntry.TABLE_NAME + " ("
                + TuesdayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TuesdayEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL); ";
        //Monday table
        String QUERY_CREATE_WEDNESDAY_TABLE = "CREATE TABLE " + WednesdayEntry.TABLE_NAME + " ("
                + WednesdayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WednesdayEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL); ";
        //Tuesday table
        String QUERY_CREATE_THURSDAY_TABLE = "CREATE TABLE " + ThursdayEntry.TABLE_NAME + " ("
                + ThursdayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ThursdayEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL); ";
        //Tuesday table
        String QUERY_CREATE_FRIDAY_TABLE = "CREATE TABLE " + FridayEntry.TABLE_NAME + " ("
                + FridayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FridayEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL); ";
        //Monday table
        String QUERY_CREATE_SATURDAY_TABLE = "CREATE TABLE " + SaturdayEntry.TABLE_NAME + " ("
                + SaturdayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SaturdayEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL); ";
        //Tuesday table
        String QUERY_CREATE_SUNDAY_TABLE = "CREATE TABLE " + SundayEntry.TABLE_NAME + " ("
                + SundayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SundayEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL); ";


        db.execSQL(QUERY_CREATE_SUBJECT_TABLE);
        db.execSQL(QUERY_CREATE_MONDAY_TABLE);
        db.execSQL(QUERY_CREATE_TUESDAY_TABLE);
        db.execSQL(QUERY_CREATE_WEDNESDAY_TABLE);
        db.execSQL(QUERY_CREATE_THURSDAY_TABLE);
        db.execSQL(QUERY_CREATE_FRIDAY_TABLE);
        db.execSQL(QUERY_CREATE_SATURDAY_TABLE);
        db.execSQL(QUERY_CREATE_SUNDAY_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getSubjectsFromDb(){
        ArrayList<String> subs = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String QUERY_GET_SUBJECTS = "SELECT "+SubjectsEntry.COLUMN_SUBJECT_NAME+ ", " +
                SubjectsEntry.COLUMN_TOTAL_CLASSES +", "+
                SubjectsEntry.COLUMN_PRESENT_CLASSES +
                " FROM " +SubjectsEntry.TABLE_NAME;
        Cursor c = db.rawQuery(QUERY_GET_SUBJECTS,null);

        while(c.moveToNext()){
            int nameColumnIndex = c.getColumnIndex(SubjectsEntry.COLUMN_SUBJECT_NAME);
            //int totalColumnIndex = c.getColumnIndex(SubjectsEntry.COLUMN_TOTAL_CLASSES);
            //int presentColumnIndex = c.getColumnIndex(SubjectsEntry.COLUMN_PRESENT_CLASSES);
            subs.add(c.getString(nameColumnIndex).trim().toUpperCase() /*+" "+
                    c.getString(totalColumnIndex).trim()+" "+
                    c.getString(presentColumnIndex).trim()*/ );
        }
        db.close();
        c.close();
        return subs;
    }

    public void addSubjectToDb(String name){
        ContentValues values = new ContentValues();
        values.put(SubjectsEntry.COLUMN_SUBJECT_NAME,name);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(SubjectsEntry.TABLE_NAME,null,values);
        db.close();
        values.clear();
    }

    public void deleteSubjectInDb(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + SubjectsEntry.TABLE_NAME + " WHERE "
                + SubjectsEntry.COLUMN_SUBJECT_NAME + "=\"" + name +"\";" );
        db.close();
    }
}
