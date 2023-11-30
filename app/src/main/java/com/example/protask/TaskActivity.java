package com.example.protask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private int taskId;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        EditText txtviewTask = findViewById(R.id.txtviewTask);
        EditText txtviewTime = findViewById(R.id.txtviewTime);
        EditText txtviewDate = findViewById(R.id.txtviewDate);
        TextView txtComp = findViewById(R.id.txtComp);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnComplete = findViewById(R.id.btnComplete);
        Button btnEdit = findViewById(R.id.btnEdit);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String tasksJson = preferences.getString("tasks", "[]");
        Intent intent = getIntent();
        taskId = (int) intent.getExtras().get("task_id");
        tasks = gson.fromJson(tasksJson, new TypeToken<List<Task>>() {}.getType());

        Task selectedTask = tasks.get(taskId);

        txtviewTask.setText(selectedTask.getName());
        txtviewTime.setText(selectedTask.getTime());
        txtviewDate.setText(selectedTask.getDate());
        if(selectedTask.isCompleted())
            txtComp.setText("Task is Done");
        else if (!selectedTask.isCompleted()) {
            txtComp.setText("Task is not Done");
        }
        btnDelete.setOnClickListener(view -> {
            deleteTask(selectedTask);
            finish();
        });

        btnComplete.setOnClickListener(view -> {
            markTaskAsCompleted(selectedTask);
            finish();
        });

        btnEdit.setOnClickListener(view -> {
            editTask(selectedTask);
            finish();
        });
    }

    private void deleteTask(Task task) {
        tasks.remove(task);
        updateTasksInSharedPreferences();
    }

    private void markTaskAsCompleted(Task task) {
        task.setCompleted(true);
        updateTasksInSharedPreferences();
    }

    private void editTask(Task task) {
        tasks.remove(task);
        String txtviewTask = ((EditText) findViewById(R.id.txtviewTask)).getText().toString();
        String txtviewTime = ((EditText) findViewById(R.id.txtviewTime)).getText().toString();
        String txtviewDate = ((EditText) findViewById(R.id.txtviewDate)).getText().toString();
        task.setName(txtviewTask);
        task.setTime(txtviewTime);
        task.setDate(txtviewDate);
        tasks.add(task);
        updateTasksInSharedPreferences();
        startActivity(new Intent(TaskActivity.this, ListActivity.class));
    }

    private void updateTasksInSharedPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String updatedTasksJson = gson.toJson(tasks);
        preferences.edit().putString("tasks", updatedTasksJson).apply();
    }
}
