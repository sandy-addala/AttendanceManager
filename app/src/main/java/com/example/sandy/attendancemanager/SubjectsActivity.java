package com.example.sandy.attendancemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sandy.attendancemanager.data.SubjectsDbHelper;

import java.util.ArrayList;
/**
 * Created by sandy on 26-06-2017.
 */

//FOR USER TO ADD HIS/HER SUBJECTS
public class SubjectsActivity extends AppCompatActivity{
    //Declaring variables
    private ListView lv;
    private EditText subNameTxt;
    private Button addBtn , deleteBtn;
    private ArrayList<String> subjects = new ArrayList<String>();
    private ArrayAdapter<String> subjectsAdapter;
    private SubjectsDbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        //INITIALIZING VARIABLES
        subNameTxt = (EditText) findViewById(R.id.subject_name);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        addBtn = (Button) findViewById(R.id.add_btn);
        deleteBtn = (Button) findViewById(R.id.delete_btn);
        mHelper = new SubjectsDbHelper(this,null,null,1);

        subjects = mHelper.getSubjectsFromDb();

        //SETTING THE ADAPTER
        subjectsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, subjects);
        lv = (ListView) findViewById(R.id.subjects_list);
        lv.setAdapter(subjectsAdapter);

        //WHEN SOMETHING IS CLICKED IN THE LISTVIEW
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subNameTxt.setText(subjects.get(position));
            }
        });

        //HANDLING THE EVENTS
        //ADDING A SUBJECT
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubject();
            }
        });

        //DELETING A SUBJECT
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSubject();
            }
        });


    }
    //METHOD FOR ADDING SUBJECTS
    private void addSubject(){
        String name = subNameTxt.getText().toString().trim().toUpperCase();

        if(!name.isEmpty() && name.length()>0 && !subjects.contains(name)){
            mHelper.addSubjectToDb(name);
            subjectsAdapter.add(name);
            //REFRESHING THE VIEW
            subjectsAdapter.notifyDataSetChanged();
            //SETTING THE VALUE TO NULL AFTER THE SUBJECT IS ADDED
            subNameTxt.setText("");
            //TOAST MEASSAGE SO THAT THE USER CAN KNOW WHAT IS HAPPENING
            Toast.makeText(getApplicationContext(), "Added " + name, Toast.LENGTH_SHORT).show();
        }
        else if(subjects.contains(name)){
            Toast.makeText(getApplicationContext(), "The subject is already entered " + name, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Enter a subject to add" , Toast.LENGTH_SHORT).show();
        }
    }

    //AND FOR DELETING THE SUBJECTS
    private void deleteSubject(){
        String name = subNameTxt.getText().toString().trim().toUpperCase();
        int pos = lv.getCheckedItemPosition();

        if(pos == -1 || name.isEmpty() || name.length()== 0){
            Toast.makeText(getApplicationContext(), "Select something to delete" , Toast.LENGTH_SHORT).show();
        }

        else if(pos > -1){
            subjectsAdapter.remove(subjects.get(pos));
            mHelper.deleteSubjectInDb(name);

            subjectsAdapter.notifyDataSetChanged();

            subNameTxt.setText("");
            lv.setItemChecked(-1,true);
            Toast.makeText(getApplicationContext(), "Deleted ", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "Nothing to Delete" , Toast.LENGTH_SHORT).show();
        }
    }


}
