package com.example.sandy.attendancemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sandy on 26-06-2017.
 */

//FOR USER TO ADD HIS/HER SUBJECTS
public class AddSubjects extends AppCompatActivity{
    //Declaring variables
    ListView lv;
    EditText subNameTxt;
    Button addBtn , updateBtn, deleteBtn;
    ArrayList<String> subjects = new ArrayList<String>();
    ArrayAdapter<String> subjectsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjects_activity);



        //INITIALIZING VARIABLES
        subNameTxt = (EditText) findViewById(R.id.subject_name);
        addBtn = (Button) findViewById(R.id.add_btn);
        updateBtn = (Button) findViewById(R.id.update_btn);
        deleteBtn = (Button) findViewById(R.id.delete_btn);

        subjects.add("JAVA");
        subjects.add("dbms");

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
        //UPDATING A SUBJECT
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSubject();
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
    //METHOD FOR UPDATING THE SUBJECTS
    private void updateSubject(){
        String name = subNameTxt.getText().toString().trim().toUpperCase();//TO GET THE NAME OF SELECTED SUBJECT
        int pos = lv.getCheckedItemPosition();//TO GET THE POSITION

        if(pos == -1){
            Toast.makeText(getApplicationContext(), "Select something to update" , Toast.LENGTH_SHORT).show();
        }

        if(!name.isEmpty() && name.length()>0 && !subjects.contains(name) && pos > -1 ){

            subjectsAdapter.remove(subjects.get(pos));
            subjectsAdapter.insert(name, pos);
            //REFRESHING
            subjectsAdapter.notifyDataSetChanged();

            subNameTxt.setText("");

            Toast.makeText(getApplicationContext(), "Updated " + name, Toast.LENGTH_SHORT).show();
        }
        else if(subjects.contains(name)){
            Toast.makeText(getApplicationContext(), "The subject is already entered " + name, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Nothing to Update" , Toast.LENGTH_SHORT).show();
        }
    }
    //AND FOR DELETING THE SUBJECTS
    private void deleteSubject(){
        int pos = lv.getCheckedItemPosition();

        if(pos == -1){
            Toast.makeText(getApplicationContext(), "Select something to delete" , Toast.LENGTH_SHORT).show();
        }

        if(pos > -1){
            subjectsAdapter.remove(subjects.get(pos));

            subjectsAdapter.notifyDataSetChanged();

            subNameTxt.setText("");
            Toast.makeText(getApplicationContext(), "Deleted ", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Nothing to Delete" , Toast.LENGTH_SHORT).show();
        }
    }


}
