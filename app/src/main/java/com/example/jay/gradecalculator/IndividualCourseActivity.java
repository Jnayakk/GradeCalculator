package com.example.jay.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.ArrayList;
import calculations.GradeCalculator;

public class IndividualCourseActivity extends Activity {
    // The data for the grade that needs to be passed to the results activity.
    public final static String IND_COURSE_GRADE = "coursegradeKey";

    private LinearLayout mLayout;
    // The EditTexts for the Individual course calculator activity
    private EditText firstAssignment;
    private EditText firstWeight;
    private EditText midtermMarks;
    private EditText midtermWeight;
    private EditText finalMarks;
    private EditText finalmarkWeight;

    private ArrayList<EditText> listofAssigns = new ArrayList<EditText>();
    private ArrayList<EditText> listofWeights = new ArrayList<EditText>();
    private ArrayList<Double> listofGrades = new ArrayList<Double>();
    private ArrayList<Double> listofWM = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_course);
        setTitle("University of Toronto");
        // setTitleColor(3);
        getActionBar().setIcon(R.drawable.piclogo);
        getActionBar()/* or getSupportActionBar() */.setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.app_name) + "</font>"));

        // Find the linear layout of the adding buttons.
        mLayout = (LinearLayout) findViewById(R.id.addLinearLayout);

        // Remove the visibility of the remove assignment button if there are no assignments.
        if ((listofAssigns.isEmpty())&&(listofWeights.isEmpty())){
            Button removeButton = (Button) findViewById(R.id.removeAsssignButton);
            removeButton.setVisibility(View.GONE);
        }
    }

    /**
     * Add more assignments to enter grades for.
     * @param view the add assignments button.
     */
    public void addAssignments(View view) {
        // Set the weight of the button to 1
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 1;

        // Create a new linear layout containing the edittexts of the assignments and weights in
        // this IndividualCourseActivity
        LinearLayout l1 = new LinearLayout(IndividualCourseActivity.this);

        // Create EditTexts for assignment and weight.
        EditText editAssignment = new EditText(IndividualCourseActivity.this);
        editAssignment.setHint("Grade");
        EditText editWeight = new EditText(IndividualCourseActivity.this);
        editWeight.setHint("Weight");

        // Set the EditTexts so that they are centered.
        editWeight.setGravity(Gravity.END);
        editAssignment.setLayoutParams(p);
        editWeight.setLayoutParams(p);

        // Add the EditTexts to the previously created linear layout.
        l1.addView(editAssignment);
        l1.addView(editWeight);

        // Add the EditTexts to our list of EditText assignments and EditText Weights.
        listofAssigns.add(editAssignment);
        listofWeights.add(editWeight);

        // Add the linearlayout to our activity to be visible.
        mLayout.addView(l1);

        // Make sure the remove assignments button is not visible when there is only one
        // EditText for assignment and weights shown.
        if ((listofAssigns.isEmpty() == false) && (listofWeights.isEmpty() == false)){
            Button removeButton = (Button) findViewById(R.id.removeAsssignButton);
            removeButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Remove the previous EditTexts for assignment and weight.
     * @param view the remove assignments button.
     */
    public void removeAssignments(View view) {

        // If there are more than one assignments added.
        if ((listofAssigns.isEmpty() == false) && (listofWeights.isEmpty() == false)){

            // Retrieve the last assignment and weight added.
            EditText a = listofAssigns.get(listofAssigns.size()-1);
            EditText w = listofWeights.get(listofWeights.size()-1);

            // Set them to be not visible and remove them from our lists of EditTexts.
            a.setVisibility(View.GONE);
            w.setVisibility(View.GONE);
            listofAssigns.remove(a);
            listofWeights.remove(w);
        }

        // Make sure the visibiltiy of the remove button is gone when there is only one assignment
        // Left.
        if ((listofAssigns.isEmpty())&&(listofWeights.isEmpty())){
            Button removeButton = (Button) findViewById(R.id.removeAsssignButton);
            removeButton.setVisibility(View.GONE);
        }
    }
    /**
     * Calculate the grade given all of the inputs
     * @param view the calculate grade button.
     */
    public void calculateGrade(View view){
        // Find all of the EditTexts in this <code>IndividualCourseActivity</code>.
        firstAssignment = (EditText) findViewById(R.id.firstAssign);
        firstWeight = (EditText) findViewById(R.id.firstWeight);
        midtermMarks = (EditText) findViewById(R.id.midAssign);
        midtermWeight = (EditText) findViewById(R.id.midWeight);
        finalMarks = (EditText) findViewById(R.id.finalAssign);
        finalmarkWeight = (EditText) findViewById(R.id.finalWeight);

        // Get all of the inputs from the EditTexts as Strings.
        String firstA = firstAssignment.getText().toString();
        String firstW = firstWeight.getText().toString();
        String midM = midtermMarks.getText().toString();
        String midW = midtermWeight.getText().toString();
        String finM = finalMarks.getText().toString();
        String finW = finalmarkWeight.getText().toString();



        for (int i = 0; i < listofAssigns.size(); i++){
            EditText assign = listofAssigns.get(i);
            EditText weight = listofWeights.get(i);

            if (((assign.getText().toString() == null))||(assign.getText().toString().isEmpty())){
                listofGrades.add(0.0);
            } else{
                listofGrades.add(Double.parseDouble(assign.getText().toString()));
            }

            if (((weight.getText().toString() == null))||(weight.getText().toString().isEmpty())){
                listofWM.add(0.0);
            } else{
                listofWM.add(Double.parseDouble(weight.getText().toString()));
            }
        }

        if(firstA == null || firstA.isEmpty()) {
            listofGrades.add(0.0);
        } else {
            listofGrades.add(Double.parseDouble(firstA));
        }
        if(firstW == null || firstW.isEmpty()) {
            listofWM.add(0.0);
        } else {
            listofWM.add(Double.parseDouble(firstW));
        }
        if(midM == null || midM.isEmpty()) {
            listofGrades.add(0.0);
        } else {
            listofGrades.add(Double.parseDouble(midM));
        }
        if(midW == null || midW.isEmpty()) {
            listofWM.add(0.0);
        } else {
            listofWM.add(Double.parseDouble(midW));
        }
        if(finM == null || finM.isEmpty()) {
            listofGrades.add(0.0);
        } else {
            listofGrades.add(Double.parseDouble(finM));
        }
        if(finW == null || finW.isEmpty()) {
            listofWM.add(0.0);
        } else {
            listofWM.add(Double.parseDouble(finW));
        }

        GradeCalculator grades = new GradeCalculator();
        for (int i = 0; i < listofGrades.size(); i++){
            grades.addMark(listofGrades.get(i), listofWM.get(i));
        }
        Double overallMark = grades.getOverallMark(grades.calculateMarksToWeight());
        Intent intent = new Intent(this, CourseResultsActivity.class);
        intent.putExtra(IND_COURSE_GRADE, overallMark.toString());
        listofWM.clear();
        listofGrades.clear();
        startActivity(intent);
    }

    public void reset (View view){
        this.recreate();
    }
}