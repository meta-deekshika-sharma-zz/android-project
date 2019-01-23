package com.example.user15.intermediateapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user15.intermediateapplication.R;

public class DialogShowActivity extends AppCompatActivity {

    private TextView dialogTextView;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_show);

        getSupportActionBar().setTitle("Dialog Result");

        dialogTextView = findViewById(R.id.dialogTextView);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        dialogTextView.setText(message);

    }
}
