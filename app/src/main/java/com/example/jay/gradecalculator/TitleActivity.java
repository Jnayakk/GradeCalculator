package com.example.jay.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class TitleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        setTitle("University of Toronto");
        // setTitleColor(3);
        getActionBar().setIcon(R.drawable.piclogo);
        getActionBar()/* or getSupportActionBar() */.setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.app_name) + "</font>"));

        /*
        // Adding an underline to the word "TORONTO"
        TextView myT = (TextView) findViewById(R.id.torunderline);
        String myString = "TORONTO";
        SpannableString content = new SpannableString(myString);
        content.setSpan(new UnderlineSpan(), 0, myString.length(), 0);
        myT.setText(content);
        */
    }

    public void toMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
