package com.example.user15.helloworldapplication.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user15.helloworldapplication.R;
import com.example.user15.helloworldapplication.fragments.SecondFragment;

public class FragmentActivity extends AppCompatActivity {

    private int firstNumber, secondNumber;
    private TextView sumTextView;
    private  int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        sumTextView = findViewById(R.id.sumTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            firstNumber = (int) bundle.get("firstNumber");
            secondNumber = (int) bundle.get("secondNumber");
            sum = firstNumber + secondNumber;

            sumTextView.setText("" + sum);
            Toast.makeText(this, "Sum : " + sum, Toast.LENGTH_SHORT).show();

            SecondFragment secondFrag = new SecondFragment();
            secondFrag.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.secondFragment, secondFrag);
            fragmentTransaction.commit();
        }
    }
}
