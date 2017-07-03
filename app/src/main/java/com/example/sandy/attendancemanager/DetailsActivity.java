package com.example.sandy.attendancemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.sandy.attendancemanager.data.DetailsDbHelper;
import com.example.sandy.attendancemanager.data.SubjectsDbHelper;

import java.util.ArrayList;

/**
 * Created by sandy on 02-07-2017.
 */

public class DetailsActivity extends AppCompatActivity {

    private SubjectsDbHelper mSubjectHelper;
    private DetailsDbHelper mDetailHelper;
    private ArrayList<String> subjects = new ArrayList<String>();
    private String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_list);

        mSubjectHelper = new SubjectsDbHelper(this,null,null,1);
        mDetailHelper = new DetailsDbHelper(this,null,null,1);

        subjects = mSubjectHelper.getSubjectsFromDb();

        ArrayList<SubjectDetails> details = new ArrayList<SubjectDetails>();

        details.add(new SubjectDetails("OVERALL",mDetailHelper.getOverallTotalClasses(),
                mDetailHelper.getOverallAttendedClasses(),mDetailHelper.getOverallPercentage(),
                mDetailHelper.getOverallMoreorLess()));

        for (int i=0 ; i< subjects.size();i++){
            sub = subjects.get(i);
            details.add(new SubjectDetails(sub,mDetailHelper.getTotalClasses(sub),
                    mDetailHelper.getAttendedClasses(sub),mDetailHelper.getPercentage(sub),
                    mDetailHelper.getMoreorLess(sub)));

        }

        SubjectAdapter adapter = new SubjectAdapter(this, details);

        ListView listView = (ListView) findViewById(R.id.list_details);

        listView.setAdapter(adapter);

    }

}
