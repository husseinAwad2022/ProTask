package com.example.protask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            String taskName = ((EditText) findViewById(R.id.txtTask)).getText().toString();
            String taskTime = ((EditText) findViewById(R.id.txtTime)).getText().toString();
            String taskDate = ((EditText) findViewById(R.id.txtDate)).getText().toString();

            Task task = new Task();
            task.setName(taskName);
            task.setTime(taskTime);
            task.setDate(taskDate);
            task.setCompleted(false);

            saveTask(task);
            startActivity(new Intent(AddActivity.this, ListActivity.class));
        });
    }
//-------------------------------------------------------------------------------
    private void saveTask(Task task) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String tasksJson = preferences.getString("tasks", "[]");

        List<Task> tasks = gson.fromJson(tasksJson, new TypeToken<List<Task>>() {}.getType());
        tasks.add(task);
        String updatedTasksJson = gson.toJson(tasks);
        preferences.edit().putString("tasks", updatedTasksJson).apply();
    }
//-------------------------------------------------------------------------------
}