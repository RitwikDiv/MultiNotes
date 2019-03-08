package com.ritwikdivakaruni.multinotes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NoteViewHolder extends RecyclerView.ViewHolder {

public TextView noteTitle;
public TextView noteUpdateTime;
public TextView noteContent;

public NoteViewHolder(View view) {
    super(view);
    noteTitle = (TextView) view.findViewById(R.id.noteTitle);
    noteUpdateTime = (TextView) view.findViewById(R.id.noteUpdateTime);
    noteContent = (TextView) view.findViewById(R.id.noteContent);
}
}
