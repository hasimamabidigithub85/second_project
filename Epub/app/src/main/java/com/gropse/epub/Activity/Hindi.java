package com.gropse.epub.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gropse.epub.Activity.Utils.Prefs;
import com.macreel.epauthi.R;

public class Hindi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi);

        findViewById(R.id.class_1_hindi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("1");
                Prefs.instanceOf(getApplicationContext()).setMedium("Hindi");
                startActivity(i);
            }
        });
        findViewById(R.id.class_2_hindi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("2");
                Prefs.instanceOf(getApplicationContext()).setMedium("Hindi");
                startActivity(i);
            }
        });
        findViewById(R.id.class_3_hindi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("3");
                Prefs.instanceOf(getApplicationContext()).setMedium("Hindi");
                startActivity(i);
            }
        });

        findViewById(R.id.class_4_hindi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("4");
                Prefs.instanceOf(getApplicationContext()).setMedium("Hindi");
                startActivity(i);
            }
        });

        findViewById(R.id.class_5_hindi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("5");
                Prefs.instanceOf(getApplicationContext()).setMedium("Hindi");
                startActivity(i);
            }
        });

        findViewById(R.id.class_6_hindi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("6");
                startActivity(i);
            }
        });

        findViewById(R.id.class_7_hindi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("7");
                Prefs.instanceOf(getApplicationContext()).setMedium("Hindi");
                startActivity(i);
            }
        });
        findViewById(R.id.class_8_hindi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Subject.class);
                Prefs.instanceOf(getApplicationContext()).setValue("8");
                Prefs.instanceOf(getApplicationContext()).setMedium("Hindi");
                startActivity(i);
            }
        });
    }
}
