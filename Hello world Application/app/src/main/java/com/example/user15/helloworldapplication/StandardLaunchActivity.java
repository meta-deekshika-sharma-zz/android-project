package com.example.user15.helloworldapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StandardLaunchActivity extends AppCompatActivity {

    private Button btnStandardLaunchActivity, btnSingleTopActivity, btnSingleTaskActivity, btnSingleInstanceActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_launch);

        Log.d("launch_mode", "Standard Launch Activity");

        btnStandardLaunchActivity = findViewById(R.id.btnStandardLaunchActivity);
        btnSingleTopActivity = findViewById(R.id.btnSingleTopActivity);
        btnSingleTaskActivity = findViewById(R.id.btnSingleTaskActivity);
        btnSingleInstanceActivity = findViewById(R.id.btnSingleInstanceActivity);

        btnStandardLaunchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StandardLaunchActivity.this, StandardLaunchActivity.class));
            }
        });

        btnSingleTopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StandardLaunchActivity.this, SingleTopActivity.class));
            }
        });

        btnSingleTaskActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StandardLaunchActivity.this, SingleTaskActivity.class));
            }
        });

        btnSingleInstanceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StandardLaunchActivity.this, SingleInstanceActivity.class));
            }
        });
    }
}
