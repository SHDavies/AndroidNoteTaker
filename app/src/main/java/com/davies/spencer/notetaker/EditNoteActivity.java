package com.davies.spencer.notetaker;

import android.annotation.TargetApi;
import android.content.Intent;
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

import java.io.Serializable;

public class EditNoteActivity extends AppCompatActivity {

    private boolean isInEditMode = true;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        final Button saveButton = (Button) findViewById(R.id.saveButton);
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);
        final TextView dateTextView = (TextView) findViewById(R.id.dateTextView);

        Serializable extra = getIntent().getSerializableExtra("Note");
        if(extra != null)
        {
            Note note = (Note) extra;
            titleEditText.setText(note.getTitle());
            noteEditText.setText(note.getNote());

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = dateFormat.format(note.getDate());

            dateTextView.setText(date);

            isInEditMode = false;
            titleEditText.setEnabled(false);
            noteEditText.setEnabled(false);
            saveButton.setText("Edit");
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED, new Intent());
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isInEditMode)
                {
                    Intent returnIntent = new Intent();
                    Note note = new Note(
                        titleEditText.getText().toString(),
                        noteEditText.getText().toString(),
                        Calendar.getInstance().getTime()
                    );
                    returnIntent.putExtra("Note", note);
                    setResult(RESULT_OK, returnIntent);
                    finish();
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
