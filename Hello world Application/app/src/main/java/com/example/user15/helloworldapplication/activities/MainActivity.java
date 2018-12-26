package com.example.user15.helloworldapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user15.helloworldapplication.R;

public class MainActivity extends AppCompatActivity {

    private Button btnStandardLaunchActivity, btnSingleTopActivity, btnSingleTaskActivity, btnSingleInstanceActivity, btnIntentActivity, btnFragmentActivity, btnFormActivity, btnRecyclerViewActivity, btnWeatherActivity, btnWeatherVolleyActivity;

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
        btnFormActivity = findViewById(R.id.btnFormActivity);
        btnRecyclerViewActivity = findViewById(R.id.btnRecyclerViewActivity);
        btnWeatherActivity = findViewById(R.id.btnWeatherActivity);
        btnWeatherVolleyActivity = findViewById(R.id.btnWeatherVolleyActivity);

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

        btnFormActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormActivity.class));
            }
        });

        btnRecyclerViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
            }
        });

        btnWeatherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra("findType", "HttpUrl");
                startActivity(intent);
            }
        });

        btnWeatherVolleyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra("findType", "Volley");
                startActivity(intent);
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
