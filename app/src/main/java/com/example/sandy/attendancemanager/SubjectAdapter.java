package com.example.sandy.attendancemanager;

import android.app.Activity;
import android.graphics.Color;
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

public class SubjectAdapter extends ArrayAdapter<SubjectDetails> {

    public SubjectAdapter(Activity context, ArrayList<SubjectDetails> subs){
        super(context,0,subs);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_subjects_list, parent, false);
        }

        SubjectDetails currentSubject = getItem(position);

        TextView subjectTextView = (TextView) listItemView.findViewById(R.id.subject_tv);
        subjectTextView.setText(currentSubject.getSubjectName());

        TextView totalTextView = (TextView) listItemView.findViewById(R.id.total_tv);
        totalTextView.setText("TOTAL : "+String.valueOf(currentSubject.getTotal()));

        TextView attendedTextView = (TextView) listItemView.findViewById(R.id.attended_tv);
        attendedTextView.setText("ATTENDED : "+String.valueOf(currentSubject.getAttended()));


        String percent = currentSubject.getPercentage();
        Float value = Float.parseFloat(percent);
        TextView percentTextView = (TextView) listItemView.findViewById(R.id.percent_tv);
        percentTextView.setText(String.valueOf(currentSubject.getPercentage()));
        if(value < 75 ) percentTextView.setTextColor(Color.RED);
        else percentTextView.setTextColor(Color.parseColor("#2E7D32"));
        percentTextView.append("%");


        TextView messageTextView = (TextView) listItemView.findViewById(R.id.message_tv);
        messageTextView.setText(currentSubject.getMessage());

        return listItemView;

    }


}
