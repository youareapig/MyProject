package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class GarageActivity extends AppCompatActivity {
    private RelativeLayout add_car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        add_car= (RelativeLayout) findViewById(R.id.add_car);
        add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GarageActivity.this,ChooseCarTypeActivity.class);
                startActivity(intent);
            }
        });
    }
}
