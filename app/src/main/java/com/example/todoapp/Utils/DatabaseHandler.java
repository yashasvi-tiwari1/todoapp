package com.example.todoapp.Utils;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todoapp.Adapter.TodoAdapter;
import com.example.todoapp.Model.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler  extends  SQLiteOpenHelper{

    private static final int VERSION =1;
    private static final String Name = "todoListDatabase";
    private static final String Todo_TABLE="todo";
    private static final String ID="id";
    private static final String TASK = "task";
    private static final String STATUS ="status";
    private static final String CREATE_TODO_TABLE= "CREATE_TABLE"+Todo_TABLE + "(" + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TASK + "TEXT," + STATUS + "INTEGER)";

    private SQLiteDatabase db;


    public DatabaseHandler(@Nullable Context context){
        super(context,Name,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Drop the older table
        //The onUpgrade() method is called when the database version is increased
        // and is used to upgrade the database schema. In this case,
        // it drops the table and recreates it.
        db.execSQL("DROP TABLE IF EXISTS"+ Todo_TABLE);
        onCreate(db);
    }
    public void openDatabase(){
        db = this.getWritableDatabase();
    }
    public void insertTask(TodoModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK,task.getTask());
        cv.put(STATUS, 0);
        db.insert(Todo_TABLE,null,cv);

    }

    @SuppressLint("Range")
    public List<TodoModel> getAllTasks(){
        List <TodoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(Boolean.parseBoolean(Todo_TABLE), null, null, null, null, null, null, null, null);
            if (cur != null){
                if (cur.moveToFirst()){
                    do{
                        TodoModel task =new TodoModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        taskList.add(task);
                    }while (cur.moveToNext());
                }
            }

        }
        finally {
            db.endTransaction();
            cur.close();
        }

        return taskList;
    }
    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();

        cv.put(STATUS, status);
        db.update(Todo_TABLE,cv, ID + "=?",new String[]{String.valueOf(id)});
    }
    public void updateTask(int id, String task){
        ContentValues cv = new ContentValues();
        cv.put(TASK,task);
        db.update(Todo_TABLE,cv,ID +"=?", new String[]{String.valueOf(id)});
    }
    public void deleteTask(int id){
        db.delete(Todo_TABLE,ID +  "=?", new String[]{String.valueOf(id)});
    }
}
