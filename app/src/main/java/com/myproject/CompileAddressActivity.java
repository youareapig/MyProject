package com.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import utils.ClassPathResource;
import utils.Global;

public class CompileAddressActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout choosecity;
    private TextView city, save_address;
    private SharedPreferences sharedPreferences;
    private EditText receive_name, receive_tel, receive_address, receive_postcode;
    private String userID;
    private Global global;
    private String addUrl;
    private String mProvince, mCity, mCounty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_address);
        //TODO 获取用户ID
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", null);
        global = new Global();
        addUrl = global.getUrl() + "api.php/Member/memberAddaddress";
        choosecity = (RelativeLayout) findViewById(R.id.choose_city);
        choosecity.setOnClickListener(this);
        city = (TextView) findViewById(R.id.city);
        save_address = (TextView) findViewById(R.id.save_address);
        save_address.setOnClickListener(this);
        receive_name = (EditText) findViewById(R.id.shr_name);
        receive_tel = (EditText) findViewById(R.id.shr_tel);
        receive_address = (EditText) findViewById(R.id.shr_address);
        receive_postcode = (EditText) findViewById(R.id.shr_postcode);




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
                        mProvince = citySelected[0];
                        mCity = citySelected[1];
                        mCounty = citySelected[2];
                        Log.i("city", "一级：" + citySelected[0] + "二级：" + citySelected[1] + "三级：" + citySelected[2]);
                    }
                });

                break;
            case R.id.save_address:

                String rName = receive_name.getText().toString().trim();
                String rTel = receive_tel.getText().toString().trim();
                String rAddress = receive_address.getText().toString().trim();
                String rPostcode = receive_postcode.getText().toString().trim();
                String rCity = city.getText().toString().trim();
                ClassPathResource classPathResource = new ClassPathResource();
                boolean isPhone = classPathResource.isMobileNO(rTel);
                if (TextUtils.isEmpty(rName) || TextUtils.isEmpty(rTel) || TextUtils.isEmpty(rAddress) || TextUtils.isEmpty(rPostcode) || TextUtils.isEmpty(rCity)) {
                    Toast.makeText(CompileAddressActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                } else {
                    if (isPhone == false) {
                        Toast.makeText(CompileAddressActivity.this, "请输入正确电话号码", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("city", "请求");
                        addSite(rTel, rName, rAddress, rPostcode);
                    }

                }

                break;
        }
    }

    private void addSite(String tel, String name, String address, String postcode) {
        RequestParams params = new RequestParams(addUrl);
        params.addBodyParameter("userid", userID);
        params.addBodyParameter("shr_phone", tel);
        params.addBodyParameter("shr_name", name);
        params.addBodyParameter("shr_province", mProvince);
        params.addBodyParameter("shr_city", mCity);
        params.addBodyParameter("shr_area", mCounty);
        params.addBodyParameter("shr_address", address);
        params.addBodyParameter("zip_code", postcode);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject mJson = new JSONObject(result);
                    String rCode = mJson.getString("code");
                    Log.i("city", rCode);
                    if (rCode.equals("3000")) {
                        Intent intent = new Intent(CompileAddressActivity.this, ManageAddressActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (rCode.equals("-3000")) {
                        Toast.makeText(CompileAddressActivity.this, "添加地址失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(CompileAddressActivity.this, "服务器故障", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
