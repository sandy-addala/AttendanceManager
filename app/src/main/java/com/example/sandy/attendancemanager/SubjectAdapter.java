package com.example.sandy.attendancemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sandy on 29-06-2017.
 */

//IGNORE THIS FILE
    //  MAY USE IT IN FUTURE

public class SubjectAdapter extends ArrayAdapter<String> {

    public SubjectAdapter( Context context, ArrayList subjects) {
        super(context, R.layout.custom_subject_rows, subjects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater subjectInflator = LayoutInflater.from(getContext());
        View customView = subjectInflator.inflate(R.layout.custom_subject_rows,parent, false);

        String sub = getItem(position);
        TextView subText = (TextView) customView.findViewById(R.id.subView);

        subText.setText(sub);
        return customView;
    }


}
