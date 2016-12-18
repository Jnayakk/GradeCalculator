package com.example.jay.gradecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
