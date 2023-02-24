package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.todoapp.Adapter.TodoAdapter;
import com.example.todoapp.Model.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView projectRecyclerView;
    private TodoAdapter tasksAdapter;

    private List<TodoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        taskList = new ArrayList<>();

         projectRecyclerView= findViewById(R.id.projectRecyclerView);
         projectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         tasksAdapter = new TodoAdapter(this);
         projectRecyclerView.setAdapter(tasksAdapter);

         TodoModel task = new TodoModel();
         task.setTask("This is a Test Task");
         task.setStatus(0);
         task.setId(1);

         taskList.add(task);
         taskList.add(task);
         taskList.add(task);
         taskList.add(task);
         taskList.add(task);

         tasksAdapter.setTasks(taskList);

    }
}