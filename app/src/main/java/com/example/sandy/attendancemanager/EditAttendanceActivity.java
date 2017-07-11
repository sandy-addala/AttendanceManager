package com.example.sandy.attendancemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sandy.attendancemanager.data.ManageDbHelper;
import com.example.sandy.attendancemanager.data.SubjectsDbHelper;

import java.util.ArrayList;

/**
 * Created by sandy on 09-07-2017.
 */

public class EditAttendanceActivity extends AppCompatActivity {

    private ArrayList<String> subjects = new ArrayList<String>();
    private SubjectsDbHelper mHelper;
    private EditText et_total;
    private EditText et_attended;
    private Button edit_changes;
    private ManageDbHelper mManageHelper;
    private int tot;
    private int att;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_attendance);

        et_total = (EditText) findViewById(R.id.set_total);
        et_attended = (EditText) findViewById(R.id.set_attended);
        edit_changes = (Button) findViewById(R.id.edit_button);



        mManageHelper = new ManageDbHelper(this,null,null,1);
        mHelper = new SubjectsDbHelper(this,null,null,1);

        subjects = mHelper.getSubjectsFromDb();

        final Spinner spinner = (Spinner) findViewById(R.id.sub_spinner);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, subjects);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);

        edit_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tot_string = et_total.getText().toString().trim();
                String att_string = et_attended.getText().toString().trim();
                if( tot_string.isEmpty() || tot_string.length()== 0) {
                    Toast.makeText(getApplicationContext(), "enter total classes", Toast.LENGTH_SHORT).show();
                }
                else if(att_string.isEmpty() || att_string.length()== 0) {
                    Toast.makeText(getApplicationContext(), "enter attended classes", Toast.LENGTH_SHORT).show();
                }
                else{
                    tot = Integer.parseInt(et_total.getText().toString());
                    att = Integer.parseInt(et_attended.getText().toString());
                    if(att>tot) {
                        Toast.makeText(getApplicationContext(), "attended classes must be less than total classes", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        mManageHelper.editAttendanceof(spinner.getSelectedItem().toString(),tot,att);
                        et_total.setText("");
                        et_attended.setText("");
                        Toast.makeText(getApplicationContext(), "Attendance for "+spinner.getSelectedItem().toString()+" is changed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
