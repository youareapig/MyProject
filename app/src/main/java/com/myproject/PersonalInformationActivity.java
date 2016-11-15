package com.myproject;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView sex, date;
    private CircleImageView userhead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        sex = (TextView) findViewById(R.id.choosesex);
        sex.setOnClickListener(this);
        date = (TextView) findViewById(R.id.choose_date);
        date.setOnClickListener(this);
        userhead = (CircleImageView) findViewById(R.id.userhead);
        userhead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choosesex:
                OptionPicker picker = new OptionPicker(this, new String[]{
                        "男", "女"
                });
                picker.setOffset(2);
                picker.setSelectedIndex(0);
                picker.setTextSize(11);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int position, String option) {
                        sex.setText(option);
                    }

                });
                picker.show();
                break;
            case R.id.choose_date:
                DatePicker picker1 = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
                //TODO 开始范围
                picker1.setRangeStart(1999, 1, 1);
                //TODO 结束范围
                picker1.setRangeEnd(2022, 1, 1);
                picker1.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        //TODO 点击确定监听事件
                        Log.d("date", "日期：" + year + month + day);
                        date.setText(year + "/" + "/" + month + "/" + day);
                    }
                });
                picker1.show();
                break;
            case R.id.userhead:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {

            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                userhead.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
