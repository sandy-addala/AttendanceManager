package com.example.sandy.attendancemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sandy.attendancemanager.data.AttendanceContract.SubjectsEntry;

import java.util.ArrayList;

/**
 * Created by sandy on 09-07-2017.
 */

public class ManageDbHelper extends SQLiteOpenHelper {

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "attendance.db";

    public ManageDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


    public void presentOn(Object o) {
        ArrayList<String> subs = (ArrayList<String>) o;
        SQLiteDatabase db = getWritableDatabase();
        for(int i=0;i<subs.size();i++){
            db.execSQL("UPDATE " + SubjectsEntry.TABLE_NAME + " SET "
                    + SubjectsEntry.COLUMN_PRESENT_CLASSES + " = "
                    + SubjectsEntry.COLUMN_PRESENT_CLASSES +" +1 "
                    + " WHERE "+ SubjectsEntry.COLUMN_SUBJECT_NAME + "=\"" + subs.get(i) +"\";" );

            db.execSQL("UPDATE " + SubjectsEntry.TABLE_NAME + " SET "
                    + SubjectsEntry.COLUMN_TOTAL_CLASSES + " = "
                    + SubjectsEntry.COLUMN_TOTAL_CLASSES +" +1 "
                    + " WHERE "+ SubjectsEntry.COLUMN_SUBJECT_NAME + "=\"" + subs.get(i) +"\";" );

        }
    }

    public void absentOn(Object o) {
        ArrayList<String> subs = (ArrayList<String>) o;
        SQLiteDatabase db = getWritableDatabase();
        for(int i=0;i<subs.size();i++){

            db.execSQL("UPDATE " + SubjectsEntry.TABLE_NAME + " SET "
                    + SubjectsEntry.COLUMN_TOTAL_CLASSES + " = "
                    + SubjectsEntry.COLUMN_TOTAL_CLASSES +" +1 "
                    + " WHERE "+ SubjectsEntry.COLUMN_SUBJECT_NAME + "=\"" + subs.get(i) +"\";" );

        }
        db.close();
    }

    public void editAttendanceof(String s, int tot, int att) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + SubjectsEntry.TABLE_NAME + " SET "
                + SubjectsEntry.COLUMN_TOTAL_CLASSES + " = "
                + tot + " WHERE "+ SubjectsEntry.COLUMN_SUBJECT_NAME + "=\"" + s +"\";" );
        db.execSQL("UPDATE " + SubjectsEntry.TABLE_NAME + " SET "
                + SubjectsEntry.COLUMN_PRESENT_CLASSES + " = "
                + att + " WHERE "+ SubjectsEntry.COLUMN_SUBJECT_NAME + "=\"" + s +"\";" );
        db.close();
    }
}
