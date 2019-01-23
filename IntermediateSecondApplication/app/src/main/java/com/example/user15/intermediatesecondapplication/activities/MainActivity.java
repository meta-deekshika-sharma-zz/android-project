package com.example.user15.intermediatesecondapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user15.intermediatesecondapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button animationBtn, customListViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationBtn = findViewById(R.id.animationBtn);
        customListViewBtn = findViewById(R.id.customListViewBtn);

        animationBtn.setOnClickListener(this);
        customListViewBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animationBtn:
                startActivity(new Intent(this, AnimationActivity.class));
                break;

            case R.id.customListViewBtn:
                startActivity(new Intent(this, CustomListActivity.class));
                break;
        }
    }
}
