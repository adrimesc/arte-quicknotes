package com.arte.quicknotes.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arte.quicknotes.MockNoteList;
import com.arte.quicknotes.R;
import com.arte.quicknotes.models.Note;

public class NoteActivity extends AppCompatActivity {

    public static final String PARAM_NOTE = "param_note";

    private EditText mTitle;
    private EditText mContent;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        setupActivity();
        loadNote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle = (EditText) findViewById(R.id.et_title);
        mContent = (EditText) findViewById(R.id.et_content);
    }

    private void loadNote() {
        Note note = (Note) getIntent().getSerializableExtra(PARAM_NOTE);
        if (note == null) {
            return;
        }

        mNote = note;
        mTitle.setText(note.getTitle());
        mContent.setText(note.getContent());
    }

    private void saveNote() {
        String title = mTitle.getText().toString();
        if (title.isEmpty()) {
            Toast.makeText(this, R.string.note_title_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (mNote == null) {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(mContent.getText().toString());
            MockNoteList.addNote(note);
        } else {
            mNote.setTitle(title);
            mNote.setContent(mContent.getText().toString());
            MockNoteList.updateNote(mNote);
        }
        finish();
    }
}
