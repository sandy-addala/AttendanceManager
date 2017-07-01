package com.example.sandy.attendancemanager.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandy.attendancemanager.R;
import com.example.sandy.attendancemanager.data.Day7DbHelper;
import com.example.sandy.attendancemanager.data.SubjectsDbHelper;

import java.util.ArrayList;

/**
 * Created by sandy on 30-06-2017.
 */

public class Day7Fragment extends Fragment {

    private Button addBtn , deleteBtn;

    private Day7DbHelper daySubs;
    private SubjectsDbHelper allSubs;

    private ArrayList<String> subjects = new ArrayList<String>();
    private ArrayList<String> subjects_on_that_day = new ArrayList<String>();
    private String[] subjectsArray;
    private String selectedSubject;
    private ArrayAdapter<String> dayAdapter;
    private ListView lv;
    TextView tv;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day7,container,false);

        tv = (TextView) view.findViewById(R.id.tv7) ;

        addBtn = (Button) view.findViewById(R.id.day7_add_btn);
        deleteBtn = (Button) view.findViewById(R.id.day7_delete_btn);
        allSubs = new SubjectsDbHelper(getActivity(),null,null,1);
        daySubs = new Day7DbHelper(getActivity(),null,null,1);
        lv = (ListView) view.findViewById(R.id.day7_list);


        subjects = allSubs.getSubjectsFromDb();
        subjectsArray = new String[subjects.size()];
        subjectsArray = subjects.toArray(subjectsArray);

        subjects_on_that_day = daySubs.getSubjectsFromDb();

        dayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_single_choice,
                subjects_on_that_day);
        lv.setAdapter(dayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(subjects_on_that_day.get(position));
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Choose a Subject");
                mBuilder.setSingleChoiceItems(subjectsArray, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedSubject = subjectsArray[which];

                    }
                });

                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        if(selectedPosition >=0){

                            daySubs.addSubjectToDb(selectedSubject);

                            subjects_on_that_day.add(selectedSubject);
                            //REFRESHING THE VIEW
                            dayAdapter.notifyDataSetChanged();

                            Toast.makeText(getActivity(), "Added " + selectedSubject , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(), "NO subject selected ", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSubject();
            }
        });

        return view;
    }


    private void deleteSubject(){
        String name = tv.getText().toString().trim().toUpperCase();
        int pos = lv.getCheckedItemPosition();


        if(pos == -1 ){
            Toast.makeText(getActivity(), "Select something to delete" , Toast.LENGTH_SHORT).show();
        }
        else{
            dayAdapter.remove(subjects_on_that_day.get(pos));
            daySubs.deleteSubjectInDb(name);

            dayAdapter.notifyDataSetChanged();

            tv.setText("");
            lv.setItemChecked(-1,true);
            Toast.makeText(getActivity(), "Deleted ", Toast.LENGTH_SHORT).show();
        }
    }

}

