package com.example.jay.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import calculations.GradeCalculator;

public class GpaCalculatorActivity extends Activity {
    // The data for the grade that needs to be passed to the results activity.
    public final static String GPA_GRADE = "gpagradeKey";
    private Spinner courseWeight;
    private Spinner gradeSpin;
    private Button btnSubmit;
    private List<String> scourse =  new ArrayList<String>();
    private List<String> sgrade =  new ArrayList<String>();
    private ArrayList<Spinner> listofWeightSpin = new ArrayList<>();
    private ArrayList<Spinner> listofGradeSpin = new ArrayList<>();
    private LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calculator);
        setTitle("University of Toronto");
        // setTitleColor(3);
        getActionBar().setIcon(R.drawable.piclogo);
        getActionBar()/* or getSupportActionBar() */.setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.app_name) + "</font>"));
        // Find the linear layout of the adding buttons.
        mLayout = (LinearLayout) findViewById(R.id.addSpinnerLayout);
        submitGpa();
        GradeCalculator grades = new GradeCalculator();
        // Add the items to the spinners
        scourse.add("No Credit: 0");
        scourse.add("Half Credit: 0.5");
        scourse.add("Full Credit: 1");

        sgrade.add("F :0-49");
        sgrade.add("D- :50-52");
        sgrade.add("D :53-56");
        sgrade.add("D+ :57-59");
        sgrade.add("C- :60-62");
        sgrade.add("C :63-66");
        sgrade.add("C+ :67-69");
        sgrade.add("B- :70-72");
        sgrade.add("B :73-76");
        sgrade.add("B+ :77-79");
        sgrade.add("A- :80-84");
        sgrade.add("A :85-89");
        sgrade.add("A+ :90-100");
    }

    public void addcourseGrade(View view) {
        // Set the weight of the button to 1
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 1;

        // Create a new linear layout containing the edittexts of the assignments and weights in
        // this IndividualCourseActivity
        LinearLayout l1 = new LinearLayout(GpaCalculatorActivity.this);

        // Create EditTexts for assignment and weight.
        Spinner weightSpin = new Spinner(GpaCalculatorActivity.this);
        Spinner gradeSpin = new Spinner(GpaCalculatorActivity.this);

        // Set the EditTexts so that they are centered.
        //editWeight.setGravity(Gravity.END);
        weightSpin.setLayoutParams(p);
        gradeSpin.setLayoutParams(p);

        // Programmatically adding the spinners now as a simple dropdown list.
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, scourse);
        weightSpin.setAdapter(spinnerArrayAdapter);
        ArrayAdapter<String> spinnerrArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sgrade);
        gradeSpin.setAdapter(spinnerrArrayAdapter);

        // Add the EditTexts to the previously created linear layout.
        l1.addView(weightSpin);
        l1.addView(gradeSpin);

        // Add the EditTexts to our list of EditText assignments and EditText Weights.
        listofWeightSpin.add(weightSpin);
        listofGradeSpin.add(gradeSpin);

        // Add the linearlayout to our activity to be visible.
        mLayout.addView(l1);
    }

    public void removecourseGrade(View view) {

        // If there are more than one assignments added.
        if ((listofWeightSpin.isEmpty() == false) && (listofGradeSpin.isEmpty() == false)) {

            // Retrieve the last assignment and weight added.
            Spinner a = listofGradeSpin.get(listofGradeSpin.size() - 1);
            Spinner w = listofWeightSpin.get(listofWeightSpin.size() - 1);

            // Set them to be not visible and remove them from our lists of EditTexts.
            a.setVisibility(View.GONE);
            w.setVisibility(View.GONE);
            listofGradeSpin.remove(a);
            listofWeightSpin.remove(w);
        }
    }

    public void submitGpa() {
        // Find the ids of the two spinners and submit button
        courseWeight = (Spinner) findViewById(R.id.course_weight);
        gradeSpin = (Spinner) findViewById(R.id.grade_spin);
        btnSubmit = (Button) findViewById(R.id.submitGpaBtn);

        final GradeCalculator grades = new GradeCalculator();
        final Intent intent = new Intent(this, GpaResultsActivity.class);

        // Set the onclicklistener of the submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // When clicked
                String firstw = String.valueOf(courseWeight.getSelectedItem());
                String firstg = String.valueOf(gradeSpin.getSelectedItem());


                double totalWeight = grades.getcourseWeight(firstw);
                double totalGrade = grades.getGradePointValue(firstg)*grades.getcourseWeight(firstw);

                // For each spinner in the list of weights and grades added
                for (int i = 0; i < listofWeightSpin.size(); i++){

                    // Get the two spinners
                    Spinner weight = listofWeightSpin.get(i);
                    Spinner grade = listofGradeSpin.get(i);

                    // Get the value selected of the two spinners
                    String w = String.valueOf(weight.getSelectedItem());
                    String g = String.valueOf(grade.getSelectedItem());

                    // Get the course weight and gpv from the selected spinner values.
                    double wDouble = grades.getcourseWeight(w);
                    double gDouble = grades.getGradePointValue(g);

                    // Add the course weight and grades received to our total course weight
                    // and grade.
                    totalWeight = totalWeight + wDouble;
                    totalGrade = totalGrade + (wDouble*gDouble);
                }
                // Round the total gpa to two decimal places
                double totalMark = totalGrade/totalWeight;
                Double totalResult = Math.round(totalMark * 100.0) / 100.0;
                intent.putExtra(GPA_GRADE, totalResult.toString());
                startActivity(intent);
            }
        });


    }
}
