package com.arte.quicknotes.models;

import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arte on 27/04/2016.
 */
public class MockNoteList {
    private static List<Note> noteList;
    private static int nextId = 0;

    public static List<Note> getList() {
        noteList = null;
        if (noteList == null) {
            createList();
        }
        return noteList;
    }

    public static void addNote(Note note) {
        note.setId(nextId);
        noteList.add(note);
        nextId += 1;
    }

    private static void createList() {
        noteList = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Note note = new Note();
            note.setTitle("Note " + (i+1));
            note.setContent("Soy una nota!");
            note.setId(nextId);
            noteList.add(note);
            nextId += 1;
        }
    }

    public static void updateNote(Note updateNote) {
        for (int i=0; i<noteList.size();i++) {
            Note note = noteList.get(i);
            if (note.getId() == updateNote.getId()){
                note.setTitle(updateNote.getTitle());
                note.setContent(updateNote.getContent());
            }
        }
    }

    public static void deleteNote(Note deleteNote) {
        for (int i=0; i<noteList.size();i++) {
            Note note = noteList.get(i);
            if (note.getId() == deleteNote.getId()){
                noteList.remove(i);
            }
        }
    }


}
