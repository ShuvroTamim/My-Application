package com.example.registration.traintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ScheduleTrain extends AppCompatActivity {

    GridView gridView;
    String[] placesName = {"Dhaka to Chattagram","Chattagram to Sylhet","Dhakat to Khulna","Dhaka to Rajshahi","Dhaka to Rangpur","Dhaka to Kishoreganj", "Dhaka to Dinajpur", "Dhaka to Sylhet"};
    int[] placeImages = {R.drawable.dh_ch,R.drawable.ch_sy,R.drawable.dh_kh,R.drawable.dh_raj,R.drawable.dh_rng,R.drawable.dh_kish,R.drawable.dh_di,R.drawable.dh_sy};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_train);

        gridView = findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), GridItemActivity.class);
                intent.putExtra("name", placesName[i]);
                intent.putExtra("image", placeImages[i]);
                startActivity(intent);
            }
        });

    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return placeImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);

            TextView name = view1.findViewById(R.id.trains);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(placesName[i]);
            image.setImageResource(placeImages[i]);
            return view1;

        }
    }
}