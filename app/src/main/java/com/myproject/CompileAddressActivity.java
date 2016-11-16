package com.myproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;

public class CompileAddressActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout choosecity;
    private TextView city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_address);
        choosecity = (RelativeLayout) findViewById(R.id.choose_city);
        choosecity.setOnClickListener(this);
        city = (TextView) findViewById(R.id.city);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_city:
                CityPicker cityPicker = new CityPicker.Builder(CompileAddressActivity.this).textSize(20)
                        .title("请选择")
                        .titleBackgroundColor("#ffffff")
                        .confirTextColor("#000000")
                        .cancelTextColor("#000000")
                        .province("四川")
                        .city("成都")
                        .district("武侯区")
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(true)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .build();
                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        city.setText(citySelected[0] + "" + citySelected[1] + "" + citySelected[2] + "");
                    }
                });

                break;
        }
    }
}
