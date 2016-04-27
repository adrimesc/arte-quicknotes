package com.arte.quicknotes;

import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class MockNoteList {

    private static final int INITIAL_NOTE_NUMBER = 5;
    private static List<Note> noteList;
    private static int nextId = 0;

    public static List<Note> getNoteList() {
        if (noteList == null) {
            createNoteList();
        }
        return noteList;
    }

    public static void addNote(Note note) {
        note.setId(nextId);
        noteList.add(note);
        nextId += 1;
    }

    public static void removeNote(Note note) {
        for (int i = 0; i < noteList.size(); i++) {
            if (note.getId() == noteList.get(i).getId()) {
                noteList.remove(i);
                return;
            }
        }
    }

    public static void updateNote(Note note) {
        for (int i = 0; i < noteList.size(); i++) {
            if (note.getId() == noteList.get(i).getId()) {
                Note existingNote = noteList.get(i);
                existingNote.setTitle(note.getTitle());
                existingNote.setContent(note.getContent());
                return;
            }
        }
    }

    private static void createNoteList() {
        noteList = new ArrayList<>();
        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dictum felis iaculis ante facilisis bibendum. Curabitur eu sem sit amet arcu semper condimentum pulvinar vitae leo. Integer placerat justo non nisl egestas semper. Phasellus dolor metus, ullamcorper sit amet consectetur id, ornare a turpis. Cras sit amet porttitor elit. Morbi at justo neque. Nulla non vulputate nunc, vitae ornare nibh. Nulla sed nulla ut velit lobortis semper. Cras maximus lobortis aliquam.";
        for (int i=0; i < INITIAL_NOTE_NUMBER; i++) {
            Note note = new Note();
            note.setTitle("Title #" + (i + 1));
            note.setContent(content);
            addNote(note);
        }
    }
}
