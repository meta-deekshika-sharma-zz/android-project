package com.example.user15.helloworldapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnStandardLaunchActivity, btnSingleTopActivity, btnSingleTaskActivity, btnSingleInstanceActivity, btnIntentActivity;
    private int firstNumber, secondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("1234", "onCreate");

        btnStandardLaunchActivity = findViewById(R.id.btnStandardLaunchActivity);
        btnSingleTopActivity = findViewById(R.id.btnSingleTopActivity);
        btnSingleTaskActivity = findViewById(R.id.btnSingleTaskActivity);
        btnSingleInstanceActivity = findViewById(R.id.btnSingleInstanceActivity);
        btnIntentActivity = findViewById(R.id.btnIntentActivity);

        btnStandardLaunchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StandardLaunchActivity.class));
            }
        });

        btnSingleTopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SingleTopActivity.class));
            }
        });

        btnSingleTaskActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SingleTaskActivity.class));
            }
        });

        btnSingleInstanceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SingleInstanceActivity.class));
            }
        });

        btnIntentActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IntentActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("1234", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("1234", "onResume");
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            firstNumber = (int) bundle.get("firstNumber");
            secondNumber = (int) bundle.get("secondNumber");
        }

        int sum = firstNumber + secondNumber;
        Toast.makeText(this, "Sum : " + sum, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("1234", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("1234", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("1234", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("1234", "onRestart");
    }
}
