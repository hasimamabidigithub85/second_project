package com.gropse.epub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;

import com.macreel.epauthi.R;

import static android.animation.ObjectAnimator.ofFloat;

public class Splash extends AppCompatActivity implements Runnable {
    Thread thread;
    ImageView splash_icon;
    TextView splash_text, welcome_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash_icon = findViewById(R.id.splash_icon);
        splash_text = findViewById(R.id.splash_text);
        welcome_text = findViewById(R.id.welcome_text);

        thread = new Thread(this);
        thread.start();


        Animation topdown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_to_down);
        splash_icon.setAnimation(topdown);
        splash_icon.setVisibility(View.VISIBLE);
        Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.begin_from_bottom);
        splash_text.startAnimation(bottomUp);
        splash_text.setVisibility(View.VISIBLE);

        Animation move_from_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_left_to_right);
        welcome_text.startAnimation(move_from_left);
        welcome_text.setVisibility(View.VISIBLE);
    }
    @Override
    public void run() {
        try {
            thread.sleep(3000);
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
