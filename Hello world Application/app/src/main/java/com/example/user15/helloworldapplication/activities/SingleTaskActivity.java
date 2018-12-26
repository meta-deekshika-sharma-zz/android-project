package com.example.user15.helloworldapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user15.helloworldapplication.R;

public class SingleTaskActivity extends AppCompatActivity {

    private Button btnStandardLaunchActivity, btnSingleTopActivity, btnSingleTaskActivity, btnSingleInstanceActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        getSupportActionBar().setTitle("Single Task");

        Log.d("launch_mode", "Single Task Activity");

        btnStandardLaunchActivity = findViewById(R.id.btnStandardLaunchActivity);
        btnSingleTopActivity = findViewById(R.id.btnSingleTopActivity);
        btnSingleTaskActivity = findViewById(R.id.btnSingleTaskActivity);
        btnSingleInstanceActivity = findViewById(R.id.btnSingleInstanceActivity);

        btnStandardLaunchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskActivity.this, StandardLaunchActivity.class));
            }
        });

        btnSingleTopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskActivity.this, SingleTopActivity.class));
            }
        });

        btnSingleTaskActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskActivity.this, SingleTaskActivity.class));
            }
        });

        btnSingleInstanceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskActivity.this, SingleInstanceActivity.class));
            }
        });
    }
}
