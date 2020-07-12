package com.example.registration.traintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchTrain extends AppCompatActivity {

    SearchView mySearchView;
    ListView myList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train);

        mySearchView = findViewById(R.id.srchtrain);
        myList = findViewById(R.id.trainlist);

        list = new ArrayList<String>();

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

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);

                return false;
            }
        });
    }
}