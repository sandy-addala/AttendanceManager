package com.example.sandy.attendancemanager.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sandy.attendancemanager.data.AttendanceContract.SubjectsEntry;
/**
 * Created by sandy on 02-07-2017.
 */

public class DetailsDbHelper extends SQLiteOpenHelper {

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "attendance.db";


    public DetailsDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public int getTotalClasses(String sub_name) {
        int classes = -1;
        SQLiteDatabase db = getWritableDatabase();

        String QUERY_GET_TOTAL = "SELECT " + SubjectsEntry.COLUMN_TOTAL_CLASSES +
                " FROM " + SubjectsEntry.TABLE_NAME +
                " WHERE " + SubjectsEntry.COLUMN_SUBJECT_NAME + "=\"" + sub_name + "\";";
        Cursor c = db.rawQuery(QUERY_GET_TOTAL, null);

        while (c.moveToNext()) {
            int nameColumnIndex = c.getColumnIndex(SubjectsEntry.COLUMN_TOTAL_CLASSES);
            classes = c.getInt(nameColumnIndex);
        }
        db.close();
        c.close();
        return classes;
    }

    public int getAttendedClasses(String sub_name){
        int classes = -1;
        SQLiteDatabase db = getWritableDatabase();

        String QUERY_GET_PRESENT = "SELECT "+SubjectsEntry.COLUMN_PRESENT_CLASSES+
                " FROM " +SubjectsEntry.TABLE_NAME +
                " WHERE "+ SubjectsEntry.COLUMN_SUBJECT_NAME  +"=\"" +sub_name +"\";";
        Cursor c = db.rawQuery(QUERY_GET_PRESENT,null);

        while(c.moveToNext()){
            int nameColumnIndex = c.getColumnIndex(SubjectsEntry.COLUMN_PRESENT_CLASSES);
            classes = c.getInt(nameColumnIndex);
        }
        db.close();
        c.close();
        return classes;
    }

    public String getPercentage(String sub_name){
        int total = getTotalClasses(sub_name);
        int attended = getAttendedClasses(sub_name);
        String percentString = "-1";
        try{

            float percent = (float)(attended*100)/total;
            float roundOff = Math.round(percent * 100) /(float) 100;
            percentString = Float.toString(roundOff);
        }
        catch (NumberFormatException ne){
            percentString = "0";
        }
        catch(ArithmeticException ae){
            percentString = "0";
        }
        return percentString;
    }

    public String getMoreorLess(String sub_name){
        String msg ="";
        int total = getTotalClasses(sub_name);
        int attended = getAttendedClasses(sub_name);
        String percentString = getPercentage(sub_name);
        float percent = Float.parseFloat(percentString);
        float  more_less = 0;
        //75 will be replaced by user's criteria
        if(percent < 75 ){
            more_less = (75*total-100*attended)/(100-75);// Should handlethe case where the user's target percentage is 100 LOL
            int temp = (int)(more_less+0.5);
            msg = "You should attend next "+temp+" calsses";
        }
        else{
            more_less = (100*attended-75*total)/75;
            int temp = (int)(more_less-0.5);
            msg = "You can skip next "+temp+" calsses";
        }
        return msg;
    }

    public int getOverallTotalClasses() {
        int tot = -1;
        SQLiteDatabase db = getWritableDatabase();

        String QUERY_GET_TOTAL = "SELECT sum(" + SubjectsEntry.COLUMN_TOTAL_CLASSES +
                ") AS total FROM " + SubjectsEntry.TABLE_NAME ;
        Cursor c = db.rawQuery(QUERY_GET_TOTAL, null);

        while (c.moveToNext()) {
            tot = c.getInt(c.getColumnIndex("total"));
        }
        db.close();
        c.close();
        return tot;
    }

    public int getOverallAttendedClasses() {
        int tot = -1;
        SQLiteDatabase db = getWritableDatabase();

        String QUERY_GET_TOTAL = "SELECT sum(" + SubjectsEntry.COLUMN_PRESENT_CLASSES +
                ") AS total FROM " + SubjectsEntry.TABLE_NAME ;
        Cursor c = db.rawQuery(QUERY_GET_TOTAL, null);

        while (c.moveToNext()) {
            tot = c.getInt(c.getColumnIndex("total"));
        }
        db.close();
        c.close();
        return tot;
    }

    public String getOverallPercentage(){
        int total = getOverallTotalClasses();
        int attended = getOverallAttendedClasses();
        String percentString = "-1";
        try{
            float percent = (float)(attended*100)/total;
            float roundOff = Math.round(percent * 100) /(float) 100;
            percentString = Float.toString(roundOff);
        }
        catch (NumberFormatException ne){
            percentString = "0";
        }
        catch(ArithmeticException ae){
            percentString = "0";
        }
        return percentString;
    }

    public String getOverallMoreorLess(){
        String msg ="";
        int total = getOverallTotalClasses();
        int attended = getOverallAttendedClasses();
        String percentString = getOverallPercentage();
        float percent = Float.parseFloat(percentString);
        float  more_less = 0;

        //75 will be replaced by user's criteria
        if(percent < 75 ){
            more_less = (75*total-100*attended)/(100-75);// Should handle the case where the user's target percentage is 100 LOL
            int temp = (int)(more_less+0.5);
            msg = "You should attend next "+temp+" classes";
        }
        else{
            more_less = (100*attended-75*total)/75;
            int temp = (int)(more_less);
            msg = "You can skip next "+temp+" classes";
        }
        return msg;
    }
}




