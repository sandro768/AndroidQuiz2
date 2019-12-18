package com.example.quiz2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 9/5/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Todo> {

    private List<Todo> todoList;

    private Context mCtx;

    public ListViewAdapter(List<Todo> todoList, Context mCtx) {
        super(mCtx, R.layout.list_items, todoList);
        this.todoList = todoList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        TextView textViewId = listViewItem.findViewById(R.id.textViewId);
        TextView textTitle = listViewItem.findViewById(R.id.textTitle);
        TextView textViewUserId = listViewItem.findViewById(R.id.textViewUserId);
        TextView textViewCompleted = listViewItem.findViewById(R.id.textViewCompleted);

        Todo todo = todoList.get(position);

        textViewId.setText(todo.getId());
        textTitle.setText(todo.getTitle());
        textViewUserId.setText(todo.getUserId());
        textViewCompleted.setText(todo.getCompleted());

        return listViewItem;
    }
}