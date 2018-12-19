package com.example.user15.helloworldapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user15.helloworldapplication.R;

public class MainActivity extends AppCompatActivity {

    private Button btnStandardLaunchActivity, btnSingleTopActivity, btnSingleTaskActivity, btnSingleInstanceActivity, btnIntentActivity, btnFragmentActivity;

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
        btnFragmentActivity = findViewById(R.id.btnFragmentActivity);

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


        btnFragmentActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
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
