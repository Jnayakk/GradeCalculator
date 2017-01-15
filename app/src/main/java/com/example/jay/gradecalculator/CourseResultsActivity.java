package com.example.jay.gradecalculator;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CourseResultsActivity extends Activity {
    private TextView resultText;
    private LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_results);
        setTitle("University of Toronto");
        // setTitleColor(3);
        getActionBar().setIcon(R.drawable.piclogo);
        getActionBar()/* or getSupportActionBar() */.setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.app_name) + "</font>"));

        String overallMark = getIntent().getStringExtra(IndividualCourseActivity.IND_COURSE_GRADE);
        resultText = new TextView(CourseResultsActivity.this);
        resultText.setText(overallMark);
        resultText.setTextSize(18);
        resultText.setTextColor(Color.BLACK);
        mLayout = (LinearLayout) findViewById(R.id.resultLayout);
        mLayout.addView(resultText);

    }
}
