package com.example.sandy.attendancemanager;

/**
 * Created by sandy on 02-07-2017.
 */

public class SubjectDetails {

    private String mSubjectName;
    private int mTotal;
    private int mAttended;
    private String mPercentage;
    private String mMessage;

    public SubjectDetails(String name,int total, int attended, String percent ,String message ){
        mSubjectName = name;
        mTotal = total;
        mAttended = attended;
        mPercentage = percent;
        mMessage = message;
    }

    public String getSubjectName() {
        return mSubjectName;
    }

    public int getTotal() {
        return mTotal;
    }

    public int getAttended() {
        return mAttended;
    }

    public String getPercentage() {
        return mPercentage;
    }

    public String getMessage() {
        return mMessage;
    }


}
