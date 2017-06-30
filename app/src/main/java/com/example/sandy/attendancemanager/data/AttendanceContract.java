package com.example.sandy.attendancemanager.data;

import android.provider.BaseColumns;

/**
 * Created by sandy on 29-06-2017.
 */

public class AttendanceContract {

    private AttendanceContract(){ }


    public static final class SubjectsEntry implements BaseColumns{

        public final static String TABLE_NAME = "subjects";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUBJECT_NAME = "name";
        public final static String COLUMN_TOTAL_CLASSES = "total";
        public final static String COLUMN_PRESENT_CLASSES = "present";
    }


}
