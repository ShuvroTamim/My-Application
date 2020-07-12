package com.example.registration.traintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectTrain extends AppCompatActivity {

    //TextView slcttxtview;
    Spinner spinner;
    Button trackLocation;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_train);

        spinner = findViewById(R.id.spnrslct);
        //slcttxtview = findViewById(R.id.slcttxtview);

        list = new ArrayList<String>();

        list.add(" ");
        list.add("SUNDARBAN EXPRESS");
        list.add("CHITRA EXPRESS");
        list.add("JAMALPUR EXPRESS");
        list.add("UPAKUL EXPRESS");
        list.add("SILKCITY EXPRESS");
        list.add("SIRAJGANJ EXPRESS");
        list.add("PADMA EXPRESS");
        list.add("PANCHAGARH EXPRESS");
        list.add("RANGPUR EXPRESS");
        list.add("NELSAGOR EXPRESS");
        list.add("PABNA EXPRESS");
        list.add("EKOTA EXPRESS");
        list.add("KURIGRAM EXPRESS");
        list.add("KAROTOYA EXPRESS");
        list.add("DHUMKETU EXPRESS");
        list.add("DRUTOJAN EXPRESS");

        spinner.setAdapter(new ArrayAdapter<>(SelectTrain.this,android.R.layout.simple_spinner_dropdown_item,list));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0){
                    //Display toast message.
                    Toast.makeText(getApplicationContext(),
                            "Please Select a Train",Toast.LENGTH_SHORT).show();
                    //Set Empty value on text view
                    //slcttxtview.setText("");
                }else {
                    //get selected value
                    //String str = adapterView.getItemAtPosition(i).toString();
                    //slcttxtview.setText(str);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        trackLocation = findViewById(R.id.trackloc);
        trackLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectTrain.this,LocationActivity.class);
                startActivity(i);
            }
        });

    }
}