package com.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.ClassPathResource;
import utils.Global;

public class ModificationAddressActivity extends AppCompatActivity {
    @BindView(R.id.modification_name)
    EditText modificationName;
    @BindView(R.id.modification_tel)
    EditText modificationTel;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.modification_city)
    RelativeLayout modificationCity;
    @BindView(R.id.modification_address)
    EditText modificationAddress;
    @BindView(R.id.modification_postcode)
    EditText modificationPostcode;
    @BindView(R.id.modification_ok)
    TextView modificationOk;
    private Unbinder unbinder;
    private Global global;
    private String url;
    private String updateaddressID, userID;
    private SharedPreferences sharedPreferences;
    private String updateprovince, updatecity, updatecounty, updatename, updatephone, updateaddr, updatepostcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_address);
        unbinder = ButterKnife.bind(this);
        global = new Global();
        url = global.getUrl() + "api.php/Member/memberChangeaddress";
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", null);
        Intent intent = getIntent();
        updatename = intent.getStringExtra("updatename");
        updatephone = intent.getStringExtra("updatephone");
        updateprovince = intent.getStringExtra("updateprovince");
        updatecity = intent.getStringExtra("updatecity");
        updatecounty = intent.getStringExtra("updatecounty");
        updateaddr = intent.getStringExtra("updateaddr");
        updatepostcode = intent.getStringExtra("updatepostcode");
        //地址ID
        updateaddressID = intent.getStringExtra("updateaddressID");
        modificationName.setText(updatename);
        modificationTel.setText(updatephone);
        city.setText(updateprovince + updatecity + updatecounty);
        modificationAddress.setText(updateaddr);
        modificationPostcode.setText(updatepostcode);
        modificationOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = modificationName.getText().toString().trim();
                String tel = modificationTel.getText().toString().trim();
                String address = modificationAddress.getText().toString().trim();
                String postid = modificationPostcode.getText().toString().trim();
                ClassPathResource classPathResource = new ClassPathResource();
                boolean isPhone = classPathResource.isMobileNO(tel);
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(tel) || TextUtils.isEmpty(city.getText().toString().trim()) || TextUtils.isEmpty(address) || TextUtils.isEmpty(postid)) {
                    Toast.makeText(ModificationAddressActivity.this, "请完善信息！", Toast.LENGTH_SHORT).show();
                } else {
                    if (isPhone == false) {
                        Toast.makeText(ModificationAddressActivity.this, "请输入正确电话号码", Toast.LENGTH_SHORT).show();
                    } else {
                        modificationAddress(tel, name, address, postid);
                    }

                }
            }
        });
        modificationCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPicker cityPicker = new CityPicker.Builder(ModificationAddressActivity.this).textSize(20)
                        .title("请选择")
                        .titleBackgroundColor("#ffffff")
                        .confirTextColor("#000000")
                        .cancelTextColor("#000000")
                        .province(updateprovince)
                        .city(updatecity)
                        .district(updatecounty)
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
                        updateprovince = citySelected[0];
                        updatecity = citySelected[1];
                        updatecounty = citySelected[2];
                        Log.i("city", "一级：" + citySelected[0] + "二级：" + citySelected[1] + "三级：" + citySelected[2]);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();

    }

    private void modificationAddress(String tel, String name, String address, String postid) {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("addr_id", updateaddressID);
        params.addBodyParameter("userid", userID);
        params.addBodyParameter("shr_phone", tel);
        params.addBodyParameter("shr_name", name);
        params.addBodyParameter("shr_province", updateprovince);
        params.addBodyParameter("shr_city", updatecity);
        params.addBodyParameter("shr_area", updatecounty);
        params.addBodyParameter("shr_address", address);
        params.addBodyParameter("zip_code", postid);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("ok", "请求成功: " + result);
                Intent intent = new Intent(ModificationAddressActivity.this, ManageAddressActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("ok", "请求失败 ");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
