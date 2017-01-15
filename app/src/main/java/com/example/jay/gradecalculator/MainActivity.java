package com.example.jay.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("University of Toronto");
       // setTitleColor(3);
        getActionBar().setIcon(R.drawable.piclogo);
        getActionBar()/* or getSupportActionBar() */.setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.app_name) + "</font>"));
    }

    public void individualCourseCalculator(View view){
        Intent intent = new Intent(this, IndividualCourseActivity.class);
        startActivity(intent);
    }

    public void gpaCalculator(View view){
        Intent intent = new Intent(this, GpaCalculatorActivity.class);
        startActivity(intent);
    }
}
