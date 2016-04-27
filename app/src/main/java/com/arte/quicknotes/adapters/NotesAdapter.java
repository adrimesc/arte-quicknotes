package com.arte.quicknotes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arte.quicknotes.R;
import com.arte.quicknotes.models.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvExcerpt;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvExcerpt = (TextView) itemView.findViewById(R.id.tv_excerpt);
        }
    }

    public interface Events {
        void onNoteClicked(Note note);
    }

    private List<Note> mNoteList;
    private Events mEvents;

    public NotesAdapter(List<Note> noteList, Events events) {
        mNoteList = noteList;
        mEvents = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = mNoteList.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvExcerpt.setText(note.getExcerpt());
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


}
