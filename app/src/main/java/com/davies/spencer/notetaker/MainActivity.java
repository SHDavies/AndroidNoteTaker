package com.davies.spencer.notetaker;

import android.annotation.TargetApi;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isInEditMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
                EditText noteEditText = (EditText) findViewById(R.id.noteEditText);

                if(isInEditMode)
                {
                    isInEditMode = false;
                    saveButton.setText("Edit");
                    titleEditText.setEnabled(false);
                    noteEditText.setEnabled(false);

                    TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    dateTextView.setText(date);
                }
                else
                {
                    isInEditMode = true;
                    saveButton.setText("Save");
                    titleEditText.setEnabled(true);
                    noteEditText.setEnabled(true);
                }
            }
        });
    }
}
