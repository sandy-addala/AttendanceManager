package com.example.sandy.attendancemanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandy.attendancemanager.data.Day1DbHelper;
import com.example.sandy.attendancemanager.data.Day2DbHelper;
import com.example.sandy.attendancemanager.data.Day3DbHelper;
import com.example.sandy.attendancemanager.data.Day4DbHelper;
import com.example.sandy.attendancemanager.data.Day5DbHelper;
import com.example.sandy.attendancemanager.data.Day6DbHelper;
import com.example.sandy.attendancemanager.data.Day7DbHelper;
import com.example.sandy.attendancemanager.data.DetailsDbHelper;
import com.example.sandy.attendancemanager.data.ManageDbHelper;

import java.util.ArrayList;

import static com.example.sandy.attendancemanager.R.id.spinner;

/**
 * Created by sandy on 03-07-2017.
 */

public class ManageAttendanceActivity extends AppCompatActivity {

    private Spinner daySpinner;
    private TextView total;
    private TextView attended;
    private TextView percent;
    private TextView msg;
    private EditText et_criteria;
    private Button add_manually;
    private Button edit_attendance;
    private DetailsDbHelper mDetailHelper;
    private Day1DbHelper dh1;
    private Day2DbHelper dh2;
    private Day3DbHelper dh3;
    private Day4DbHelper dh4;
    private Day5DbHelper dh5;
    private Day6DbHelper dh6;
    private Day7DbHelper dh7;
    private ManageDbHelper mManageHelper;
    private Button add_attendance;
    private RelativeLayout rl;
    private ArrayList<String> subjects = new ArrayList<String>();
    private ArrayList<Object> time_table = new ArrayList<>();
    private String mark;
    private String criteria_string;
    private int criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_attendance);
        displayOverallAttendance();

        daySpinner = (Spinner) findViewById(spinner);

        add_attendance = (Button) findViewById(R.id.add_attendance_btn);

        add_manually = (Button) findViewById(R.id.add_manually);

        edit_attendance = (Button) findViewById(R.id.edit_attendance);



        mManageHelper = new ManageDbHelper(this,null,null,1);
        dh1 = new Day1DbHelper(this,null,null,1);
        subjects = dh1.getSubjectsFromDb();
        time_table.add(subjects);
        dh2 = new Day2DbHelper(this,null,null,1);
        subjects = dh2.getSubjectsFromDb();
        time_table.add(subjects);
        dh3 = new Day3DbHelper(this,null,null,1);
        subjects = dh3.getSubjectsFromDb();
        time_table.add(subjects);
        dh4 = new Day4DbHelper(this,null,null,1);
        subjects = dh4.getSubjectsFromDb();
        time_table.add(subjects);
        dh5 = new Day5DbHelper(this,null,null,1);
        subjects = dh5.getSubjectsFromDb();
        time_table.add(subjects);;
        dh6 = new Day6DbHelper(this,null,null,1);
        subjects = dh6.getSubjectsFromDb();
        time_table.add(subjects);
        dh7 = new Day7DbHelper(this,null,null,1);
        subjects = dh7.getSubjectsFromDb();
        time_table.add(subjects);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(adapter);



        add_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = daySpinner.getSelectedItemPosition();

                if(mark=="present"){
                    mManageHelper.presentOn(time_table.get(day));
                    Toast.makeText(getApplicationContext(),"present on "+daySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    displayOverallAttendance();
                }
                else if(mark=="absent"){
                    mManageHelper.absentOn(time_table.get(day));
                    Toast.makeText(getApplicationContext(),"absent on "+daySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    displayOverallAttendance();
                }
                else
                    Toast.makeText(getApplicationContext(), "Select present or absent", Toast.LENGTH_SHORT).show();
            }
        });

        add_manually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ManageAttendanceActivity.this,ManualAttendanceActivity.class);
                startActivity(intent);
            }
        });

        edit_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ManageAttendanceActivity.this,EditAttendanceActivity.class);
                startActivity(intent);
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
                Toast.makeText(getApplicationContext(), "Seleect something asshole", Toast.LENGTH_SHORT).show();
        }
        displayOverallAttendance();
    }

    public void displayOverallAttendance(){

        mDetailHelper = new DetailsDbHelper(this,null,null,1);

        rl = (RelativeLayout) findViewById(R.id.relative_view);
        total = (TextView) findViewById(R.id.total_tv1);
        attended = (TextView) findViewById(R.id.attended_tv1);
        percent = (TextView) findViewById(R.id.percent_tv1);
        msg = (TextView) findViewById(R.id.message_tv1);

        total.setText("TOTAL : "+String.valueOf(mDetailHelper.getOverallTotalClasses()));
        attended.setText("ATTENDED : "+String.valueOf(mDetailHelper.getOverallAttendedClasses()));
        percent.setText(mDetailHelper.getOverallPercentage());
        msg.setText(mDetailHelper.getOverallMoreorLess());
        String p = mDetailHelper.getOverallPercentage();
        Float value = Float.parseFloat(p);
        int c = getCriteria();
        if(value < c ) rl.setBackgroundColor(Color.parseColor("#EF9A9A"));
        else rl.setBackgroundColor(Color.parseColor("#C5E1A5"));
        percent.append("%");

    }

    public int getCriteria(){
        et_criteria = (EditText) findViewById(R.id.criteria);
        criteria_string = et_criteria.getText().toString();
        criteria = Integer.parseInt(criteria_string);
        return criteria;
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        displayOverallAttendance();
    }
}
