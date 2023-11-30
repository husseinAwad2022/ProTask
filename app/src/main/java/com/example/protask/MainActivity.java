package com.example.protask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnViewList;
    Button btnAddTask;

    TextView txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWelcome = findViewById(R.id.btnAddTask);
        btnViewList = findViewById(R.id.btnViewList);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnViewListOnClick();
        btnAddTaskOnClick();
    }

    public void btnViewListOnClick() {
        btnViewList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        });
    }

    public void btnAddTaskOnClick() {
        btnAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });
    }
}