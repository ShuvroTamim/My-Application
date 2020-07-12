package com.example.registration.traintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {

    Button schedule, location, btnsheba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selection);

        schedule = findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SelectionActivity.this,ScheduleTrain.class);
                startActivity(i);

            }
        });

        location = findViewById(R.id.tracker);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectionActivity.this,SelectTrain.class);
                startActivity(i);
            }
        });

    }

    public void sheba(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.esheba.cnsbd.com/#/"));
        startActivity(i);
    }
}
