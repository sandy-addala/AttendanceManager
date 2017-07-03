package com.example.sandy.attendancemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by sandy on 03-07-2017.
 */

public class ManageAttendanceActivity extends Activity implements OnItemSelectedListener {

    private Spinner daySpinner;
    private String daySelected;
    TextView nigga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_attendance);

        daySpinner = (Spinner) findViewById(R.id.spinner);

        nigga = (TextView) findViewById(R.id.tdew);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(adapter);

        daySelected = daySpinner.getSelectedItem().toString();

        nigga.setText(daySelected);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        nigga.setText(daySelected);
    }
}
