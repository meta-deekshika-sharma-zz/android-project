package com.example.user15.intermediateapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.user15.intermediateapplication.R;

public class CustomDrawableActivity extends AppCompatActivity {

    private Switch enableButton;
    private Button stateChangedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawable);

        enableButton = findViewById(R.id.enableButton);
        stateChangedButton = findViewById(R.id.stateChangedButton);
        getSupportActionBar().setTitle("Custom Drawable");

        enableButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                   stateChangedButton.setEnabled(true);
                else
                    stateChangedButton.setEnabled(false);
            }
        });
    }
}
