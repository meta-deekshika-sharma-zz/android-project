package com.example.user15.intermediatesecondapplication.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.user15.intermediatesecondapplication.R;
import com.example.user15.intermediatesecondapplication.fragments.FirstFragment;
import com.example.user15.intermediatesecondapplication.fragments.SecondFragment;

import java.util.Random;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private FrameLayout frameLayout;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private LinearLayout linearLayout;
    private MaterialButton rotateBtn, transparentBtn, moveBtn, scaleBtn;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        tabLayout = findViewById(R.id.tabLayout);
        linearLayout = findViewById(R.id.linearLayout);
        frameLayout = findViewById(R.id.frameLayout);
        rotateBtn = findViewById(R.id.rotateBtn);
        moveBtn = findViewById(R.id.moveBtn);
        transparentBtn = findViewById(R.id.transparentBtn);
        scaleBtn = findViewById(R.id.scaleBtn);
        tabLayout.addTab(tabLayout.newTab().setText("Fragment 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Fragment 2"));

        rotateBtn.setOnClickListener(this);
        transparentBtn.setOnClickListener(this);
        moveBtn.setOnClickListener(this);
        scaleBtn.setOnClickListener(this);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        firstFragment = new FirstFragment();
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.frameLayout, firstFragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        secondFragment = new SecondFragment();
                        frameLayout.removeAllViews();
                        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragmentTransaction.replace(R.id.frameLayout, secondFragment);
                        fragmentTransaction.commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                int red = new Random().nextInt(256);
                int green = new Random().nextInt(256);
                int blue = new Random().nextInt(256);
                linearLayout.setBackgroundColor(Color.rgb(red, green, blue));
                start();
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rotateBtn:
                animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                frameLayout.startAnimation(animation);
                break;

            case R.id.moveBtn:
                animation = AnimationUtils.loadAnimation(this, R.anim.translate);
                frameLayout.startAnimation(animation);
                break;

            case R.id.transparentBtn:
                animation = AnimationUtils.loadAnimation(this, R.anim.transparent);
                frameLayout.startAnimation(animation);
                break;

            case R.id.scaleBtn:
                animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
                frameLayout.startAnimation(animation);
                break;
        }
    }
}
