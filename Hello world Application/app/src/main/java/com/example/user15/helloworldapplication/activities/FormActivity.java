package com.example.user15.helloworldapplication.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.user15.helloworldapplication.R;
import com.example.user15.helloworldapplication.fragments.ShowFormDataFragment;

public class FormActivity extends AppCompatActivity {

    private String name, email, number, dob, gender, state, city;
    private boolean aadharCard, panCard, voterCard, drivingLicence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getSupportActionBar().setTitle("Form");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {
            name = bundle.getString("name");
            email = bundle.getString("email");
            number = bundle.getString("number");
            dob = bundle.getString("dob");
            gender = bundle.getString("gender");
            aadharCard = bundle.getBoolean("aadharCard");
            panCard = bundle.getBoolean("panCard");
            voterCard = bundle.getBoolean("voterCard");
            drivingLicence = bundle.getBoolean("drivingLicence");
            state = bundle.getString("state");
            city = bundle.getString("city");

            ShowFormDataFragment showFormDataFragment = new ShowFormDataFragment();
            showFormDataFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();;
            fragmentTransaction.replace(R.id.formFragment, showFormDataFragment);
            fragmentTransaction.commit();
        }
    }
}
