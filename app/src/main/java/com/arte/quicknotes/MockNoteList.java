package com.arte.quicknotes;

import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class MockNoteList {

    private static final int INITIAL_NOTE_NUMBER = 5;
    private static final String IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dictum felis iaculis ante facilisis bibendum. Curabitur eu sem sit amet arcu semper condimentum pulvinar vitae leo. Integer placerat justo non nisl egestas semper. Phasellus dolor metus, ullamcorper sit amet consectetur id, ornare a turpis. Cras sit amet porttitor elit. Morbi at justo neque. Nulla non vulputate nunc, vitae ornare nibh. Nulla sed nulla ut velit lobortis semper. Cras maximus lobortis aliquam.";
    private static List<Note> noteList;

    public static List<Note> getNoteList() {
        if (noteList == null) {
            createNoteList();
        }
        return noteList;
    }

    public static void addNote(Note note) {
        noteList.add(note);
    }

    private static void createNoteList() {
        noteList = new ArrayList<>();

        for (int i=0; i < INITIAL_NOTE_NUMBER; i++) {
            Note note = new Note();
            note.setTitle("Title #" + (i + 1));
            note.setContent(IPSUM);
            addNote(note);
        }
    }
}
