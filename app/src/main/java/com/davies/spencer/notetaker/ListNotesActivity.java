package com.davies.spencer.notetaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListNotesActivity extends AppCompatActivity {

    private List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        notes.add(new Note("First note", "Hi", new Date()));
        notes.add(new Note("Second note", "Hi", new Date()));
        notes.add(new Note("Third note", "Hi", new Date()));
        notes.add(new Note("Fourth note", "Hi", new Date()));
        notes.add(new Note("Fifth note", "Hi", new Date()));

        populateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        notes.add(new Note("Added note", "Hi", new Date()));

        populateList();

        return true;
    }

    private void populateList() {
        ListView notesListView = (ListView) findViewById(R.id.notesListView);

        List<String> values = new ArrayList<String>();
        for (Note note : notes)
        {
            values.add(note.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                values
        );
        notesListView.setAdapter(adapter);
    }
}
