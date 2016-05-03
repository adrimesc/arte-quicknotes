package com.arte.quicknotes.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arte.quicknotes.R;
import com.arte.quicknotes.adapters.NotesAdapter;
import com.arte.quicknotes.database.NotesDataSource;
import com.arte.quicknotes.database.SQLHelper;
import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.Events {
    private NotesAdapter mAdapter;
    private SQLHelper sqlManager;
    private NotesDataSource notesDbm = new NotesDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlManager = new SQLHelper(this);
        sqlManager.getReadableDatabase();
        setupActivity();
    }

    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Context context = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoteActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_notes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, Boolean.FALSE);

        notesDbm.open();
        Cursor cursor = notesDbm.getAllNotes();

        List<Note> notes = new ArrayList<>();
        Note note;
        if (cursor.moveToFirst()) {
            do {
                note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                notes.add(note);
            } while (cursor.moveToNext());
        }

        notesDbm.close();
        mAdapter = new NotesAdapter(notes, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    protected void onResume() {
        super.onResume();
        sqlManager = new SQLHelper(this);
        sqlManager.getReadableDatabase();
        setupActivity();
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAdapter.notifyDataSetChanged();
    }

    public void onNoteClicked(Note note) {
        Intent intent = new Intent(this, NoteActivity.class);
        Bundle arguments = new Bundle();
        arguments.putSerializable(NoteActivity.PARAM_NOTE, note);
        intent.putExtras(arguments);
        startActivityForResult(intent,0);
    }

}
