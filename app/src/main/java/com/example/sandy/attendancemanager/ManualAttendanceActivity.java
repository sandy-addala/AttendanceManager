package com.example.sandy.attendancemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sandy.attendancemanager.data.ManageDbHelper;
import com.example.sandy.attendancemanager.data.SubjectsDbHelper;

import java.util.ArrayList;

/**
 * Created by sandy on 09-07-2017.
 */

public class ManualAttendanceActivity  extends AppCompatActivity {
    private String mark;
    private Button ok;
    private ListView lv;
    private ArrayList<String> subjects = new ArrayList<String>();
    private ArrayAdapter<String> subjectsAdapter;
    private SubjectsDbHelper mHelper;
    private ManageDbHelper mManageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_activity);

        ok = (Button) findViewById(R.id.ok_button);

        mHelper = new SubjectsDbHelper(this,null,null,1);

        subjects = mHelper.getSubjectsFromDb();
        mManageHelper = new ManageDbHelper(this,null,null,1);

        subjectsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, subjects);
        lv = (ListView) findViewById(R.id.subjects_list1);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(subjectsAdapter);

        ok.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ArrayList<String> selected_subjects = new ArrayList<String>();
                int cntChoice = lv.getCount();

                SparseBooleanArray sparseBooleanArray = lv.getCheckedItemPositions();

                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)){
                        selected_subjects.add(lv.getItemAtPosition(i).toString());
                    }
                }

                if(mark=="present"){
                    mManageHelper.presentOn(selected_subjects);
                    Toast.makeText(getApplicationContext(),"present for"+selected_subjects, Toast.LENGTH_SHORT).show();
                }
                else if(mark=="absent"){
                    mManageHelper.absentOn(selected_subjects);
                    Toast.makeText(getApplicationContext(),"absent for"+selected_subjects, Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Select present or absent", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.present:
                if (checked)
                    mark = "present";
                break;
            case R.id.absent:
                if (checked)
                    mark = "absent";
                break;
            default :
                Toast.makeText(getApplicationContext(), "Select something asshole", Toast.LENGTH_SHORT).show();
        }
    }

}
