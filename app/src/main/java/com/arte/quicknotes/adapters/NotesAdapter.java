package com.arte.quicknotes.adapters;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arte.quicknotes.R;
import com.arte.quicknotes.models.Note;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by arte on 27/04/2016.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    public  interface Events {
        void onNoteClicked(Note note);

    }

    public List<Note> mNoteList;
    public Events mEvents;

    public NotesAdapter(List<Note> notes, Events events)
    {
        mNoteList = notes;
        mEvents = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
        final Note note = mNoteList.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteExcept.setText(note.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEvents.onNoteClicked(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView noteTitle;
        private TextView noteExcept;
        public ViewHolder(View itemView) {
            super(itemView);

            noteTitle = (TextView) itemView.findViewById(R.id.tv_title);
            noteExcept = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }


}