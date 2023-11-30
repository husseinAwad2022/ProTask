package com.example.protask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    Gson gson;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayTasks();
    }

    private void displayTasks() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        gson = new Gson();
        String tasksJson = preferences.getString("tasks", "[]");

        List<Task> tasks = gson.fromJson(tasksJson, new TypeToken<List<Task>>() {}.getType());

        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        ListView listTasks = findViewById(R.id.listTasks);
        listTasks.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener = (parent, view, position, id) -> {
            Intent intent = new Intent(ListActivity.this,
                    TaskActivity.class);
            intent.putExtra("task_id", (int)id);
            startActivity(intent);

        };
        listTasks.setOnItemClickListener(itemClickListener);
    }
}