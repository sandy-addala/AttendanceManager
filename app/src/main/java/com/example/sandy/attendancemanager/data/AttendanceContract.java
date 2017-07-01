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

    public static final class MondayEntry implements BaseColumns{

        public final static String TABLE_NAME = "monday";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUBJECT_NAME = "name";
    }

    public static final class TuesdayEntry implements BaseColumns{

        public final static String TABLE_NAME = "tuesday";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUBJECT_NAME = "name";
    }

    public static final class WednesdayEntry implements BaseColumns{

        public final static String TABLE_NAME = "wednesdsay";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUBJECT_NAME = "name";
    }

    public static final class ThursdayEntry implements BaseColumns{

        public final static String TABLE_NAME = "thursday";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUBJECT_NAME = "name";
    }

    public static final class FridayEntry implements BaseColumns{

        public final static String TABLE_NAME = "friday";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUBJECT_NAME = "name";
    }

    public static final class SaturdayEntry implements BaseColumns{

        public final static String TABLE_NAME = "saturday";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUBJECT_NAME = "name";
    }

    public static final class SundayEntry implements BaseColumns{

        public final static String TABLE_NAME = "sunday";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUBJECT_NAME = "name";
    }


}
