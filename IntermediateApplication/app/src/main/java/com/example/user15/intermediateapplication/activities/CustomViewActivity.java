package com.example.user15.intermediateapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user15.intermediateapplication.R;
import com.example.user15.intermediateapplication.views.CustomEditTextView;

public class CustomViewActivity extends AppCompatActivity {

    private CustomEditTextView customEditTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        getSupportActionBar().setTitle("Custom View");

        customEditTextView = new CustomEditTextView(this);
    }
}
