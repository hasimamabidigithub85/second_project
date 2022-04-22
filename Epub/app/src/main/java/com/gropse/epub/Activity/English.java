package com.gropse.epub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gropse.epub.Activity.Utils.Prefs;
import com.macreel.epauthi.R;

public class English extends AppCompatActivity {
    LinearLayout eng_layout, hind_layout, urdu_layout;
    String engStr, hindiStr, urduStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);


        findViewById(R.id.class1_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("1");
                Prefs.instanceOf(getApplicationContext()).setMedium("English");
                startActivity(i);
            }
        });
         findViewById(R.id.class_2_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("2");
                Prefs.instanceOf(getApplicationContext()).setMedium("English");
                startActivity(i);
            }
        });

        findViewById(R.id.class_3_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("3");
                Prefs.instanceOf(getApplicationContext()).setMedium("English");
                startActivity(i);
            }
        });

        findViewById(R.id.class_4_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("4");
                Prefs.instanceOf(getApplicationContext()).setMedium("English");
                startActivity(i);
            }
        });

        findViewById(R.id.class_5_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("5");
                Prefs.instanceOf(getApplicationContext()).setMedium("English");
                startActivity(i);
            }
        });

        findViewById(R.id.class_6_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("6");
                Prefs.instanceOf(getApplicationContext()).setMedium("English");
                startActivity(i);
            }
        });
        findViewById(R.id.class_7_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("7");
                Prefs.instanceOf(getApplicationContext()).setMedium("English");
                startActivity(i);
            }
        });
        findViewById(R.id.class_8_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("8");
                Prefs.instanceOf(getApplicationContext()).setMedium("English");
                startActivity(i);
            }
        });
    }
}
