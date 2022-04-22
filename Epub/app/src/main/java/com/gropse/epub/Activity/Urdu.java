package com.gropse.epub.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gropse.epub.Activity.Utils.Prefs;
import com.macreel.epauthi.R;

public class Urdu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urdu);

        findViewById(R.id.class_1_urdu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("1");
                Prefs.instanceOf(getApplicationContext()).setMedium("Urdu");
                startActivity(i);
            }
        });
        findViewById(R.id.class_2_urdu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("2");
                Prefs.instanceOf(getApplicationContext()).setMedium("Urdu");
                startActivity(i);
            }
        });
        findViewById(R.id.class_3_urdu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("3");
                Prefs.instanceOf(getApplicationContext()).setMedium("Urdu");
                startActivity(i);
            }
        });

        findViewById(R.id.class_4_urdu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("4");
                Prefs.instanceOf(getApplicationContext()).setMedium("Urdu");
                startActivity(i);
            }
        });

        findViewById(R.id.class_5_urdu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("5");
                Prefs.instanceOf(getApplicationContext()).setMedium("Urdu");
                startActivity(i);
            }
        });

        findViewById(R.id.class_6_urdu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("6");
                Prefs.instanceOf(getApplicationContext()).setMedium("Urdu");
                startActivity(i);
            }
        });

        findViewById(R.id.class_7_urdu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("7");
                Prefs.instanceOf(getApplicationContext()).setMedium("Urdu");
                startActivity(i);
            }
        });
        findViewById(R.id.class_8_urdu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("8");
                Prefs.instanceOf(getApplicationContext()).setMedium("Urdu");
                startActivity(i);
            }
        });
    }
}
