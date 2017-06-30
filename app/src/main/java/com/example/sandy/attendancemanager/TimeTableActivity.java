package com.example.sandy.attendancemanager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.sandy.attendancemanager.fragments.Day1Fragment;
import com.example.sandy.attendancemanager.fragments.Day2Fragment;
import com.example.sandy.attendancemanager.fragments.Day3Fragment;
import com.example.sandy.attendancemanager.fragments.Day4Fragment;
import com.example.sandy.attendancemanager.fragments.Day5Fragment;
import com.example.sandy.attendancemanager.fragments.Day6Fragment;
import com.example.sandy.attendancemanager.fragments.Day7Fragment;

/**
 * Created by sandy on 30-06-2017.
 */

public class TimeTableActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Day1Fragment(), "MON");
        adapter.addFragment(new Day2Fragment(), "TUE");
        adapter.addFragment(new Day3Fragment(), "WED");
        adapter.addFragment(new Day4Fragment(), "THU");
        adapter.addFragment(new Day5Fragment(), "FRI");
        adapter.addFragment(new Day6Fragment(), "SAT");
        adapter.addFragment(new Day7Fragment(), "Sun");
        viewPager.setAdapter(adapter);
    }



}
