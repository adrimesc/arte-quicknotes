package com.arte.quicknotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.arte.quicknotes.models.Note;


public class NotesDataSource {

    private SQLiteDatabase db;
    private SQLHelper dbHelper;
    private boolean dbEmpty = true;

    public NotesDataSource(Context context) {
        dbHelper = new SQLHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createNote(String title, String content) {
        ContentValues values = getContentValues(title, content);
        db.insert(SQLHelper.NoteEntry.TABLE_NAME, null, values);
    }

    public void updateNote(long id, String title, String content) {
        ContentValues values = getContentValues(title, content);
        String whereClause = SQLHelper.NoteEntry._ID + " = ?";
        String[] args = { String.valueOf(id) };
        db.update(SQLHelper.NoteEntry.TABLE_NAME, values, whereClause, args);
    }

    public void deleteNote(Note note) {
        long deleteId = note.getId();
        String[] args = { String.valueOf(deleteId) };
        db.delete(SQLHelper.NoteEntry.TABLE_NAME,
                SQLHelper.NoteEntry._ID + " = ?",
                args);
    }

    public void deleteAllNotes() {
        db.delete(SQLHelper.NoteEntry.TABLE_NAME, null, null);
    }

    public Cursor getAllNotes() {
        String[] projection = {
                SQLHelper.NoteEntry._ID,
                SQLHelper.NoteEntry.COLUMN_NAME_TITLE,
                SQLHelper.NoteEntry.COLUMN_NAME_CONTENT
        };
        String sortOrder =
                SQLHelper.NoteEntry._ID + " DESC";

        return db.query(
                SQLHelper.NoteEntry.TABLE_NAME,     // The table to query
                projection,                             // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );
    }

    private ContentValues getContentValues(String title, String content) {
        ContentValues values = new ContentValues();
        values.put(SQLHelper.NoteEntry.COLUMN_NAME_TITLE, title);
        values.put(SQLHelper.NoteEntry.COLUMN_NAME_CONTENT, content);
        return values;
    }

}
