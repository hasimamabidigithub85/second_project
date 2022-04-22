package com.gropse.epub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.macreel.epauthi.R;

public class MainActivity extends AppCompatActivity {
    CardView english_cardview,hindi_cardview,urdu_cardview,teacher_training_cardview;

    Button send_btn;
    int k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        english_cardview = findViewById(R.id.english_cardview);
        hindi_cardview = findViewById(R.id.hindi_cardview);
        urdu_cardview = findViewById(R.id.urdu_cardview);
        teacher_training_cardview = findViewById(R.id.teacher_training_cardview);
       // send_btn = findViewById(R.id.send_btn);



        english_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,English.class);
                startActivity(i);
            }
        });
        hindi_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Hindi.class);
              //  i.putExtra("h","hindi_layout");
                startActivity(i);

            }
        });

        urdu_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Urdu.class);
                //i.putExtra("urdu","urdu_layout");
                startActivity(i);

            }
        });

        teacher_training_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TeacherTrainingMatrial.class);
                //i.putExtra("urdu","urdu_layout");
                startActivity(i);

            }
        });

       /* send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (k==1) {
                    Intent i = new Intent(getApplicationContext(), English.class);
                    startActivity(i);


                }
                else {
                    Toast.makeText(MainActivity.this, "Select Your Language", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
