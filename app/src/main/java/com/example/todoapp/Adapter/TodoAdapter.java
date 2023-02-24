package com.example.todoapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import  androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.Model.TodoModel;
import com.example.todoapp.R;

import java.util.List;
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>{
private List<TodoModel> todoList;
private MainActivity activity;

    public TodoAdapter(MainActivity activity) {
        this.activity = activity;
    }
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View itemView = LayoutInflater.from(parent.getContext())
             .inflate(R.layout.task_layout,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        TodoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
    }
    @Override
    public int getItemCount() {
        Log.d( "CheckBox", "getItemCount: +"+todoList.size());
        return todoList.size();
    }
    private  boolean toBoolean(int n){
        return n!=0;
    }
    public void setTasks(List<TodoModel>todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        public ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.projectCheckBox);
        }
    }


}
