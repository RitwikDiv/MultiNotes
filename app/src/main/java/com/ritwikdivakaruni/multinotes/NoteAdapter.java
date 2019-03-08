package com.ritwikdivakaruni.multinotes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.util.Log;

import java.util.List;


class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private static final String TAG = "Note Adapter";
    private List<note> noteList;
    private MainActivity mainActivity;

    public NoteAdapter(List<note> noteList, MainActivity mainActivity){
        this.noteList = noteList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        itemView.setOnClickListener(mainActivity);
        itemView.setOnLongClickListener(mainActivity);

        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Filling View Holder");
        note note = noteList.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteUpdateTime.setText(note.getUpdateTime());
        String contentDisplay;
        if(note.getContent().length() > 80) {
            contentDisplay = note.getContent().substring(0, 79) + "...";
        }
        else {
            contentDisplay = note.getContent();
        }
        holder.noteContent.setText(contentDisplay);
    }
    @Override
    public int getItemCount() {
        return this.noteList.size();
    }
}

