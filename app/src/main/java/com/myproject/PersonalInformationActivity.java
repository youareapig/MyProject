package com.myproject;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.OptionalInt;

import bean.UserInformationBean;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;


import de.hdodenhof.circleimageview.CircleImageView;
import utils.Global;

public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView sex, date, save, mUsername;
    private CircleImageView userhead;
    private SharedPreferences sharedPreferences;
    private ProgressDialog progressDialog = null;
    private String URL, HeadUrl, userID, saveURL;
    private Global global;
    private String headerName;
    private RelativeLayout informationusername1, userhead1, choosesex1, choose_date1,information_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", null);
        global = new Global();
        //TODO 获取个人信息接口
        URL = global.getUrl() + "api.php/Member/person";
        //TODO 上传头像接口
        HeadUrl = global.getUrl() + "api.php/Member/Setphoto";
        //TODO 保存信息接口
        saveURL = global.getUrl() + "api.php/Member/Setperson";

        sex = (TextView) findViewById(R.id.choosesex);
        sex.setOnClickListener(this);
        date = (TextView) findViewById(R.id.choose_date);
        save = (TextView) findViewById(R.id.save);
        informationusername1 = (RelativeLayout) findViewById(R.id.informationusername1);
        userhead1 = (RelativeLayout) findViewById(R.id.userhead1);
        userhead1.setOnClickListener(this);
        informationusername1.setOnClickListener(this);
        choosesex1 = (RelativeLayout) findViewById(R.id.choosesex1);
        choosesex1.setOnClickListener(this);
        choose_date1 = (RelativeLayout) findViewById(R.id.choose_date1);
        choose_date1.setOnClickListener(this);
        mUsername = (TextView) findViewById(R.id.informationusername);
        save.setOnClickListener(this);
        date.setOnClickListener(this);
        userhead = (CircleImageView) findViewById(R.id.userhead);
        information_back= (RelativeLayout) findViewById(R.id.information_back);
        information_back.setOnClickListener(this);
        visitInformation();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choosesex1:
                OptionPicker picker = new OptionPicker(this, new String[]{
                        "男", "女"
                });
                picker.setOffset(2);
                //picker.setSelectedIndex(0);
                picker.setTextSize(11);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int position, String option) {
                        sex.setText(option);
                    }

                });
                picker.show();
                break;
            case R.id.choose_date1:
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
                        date.setText(year + "" + "-" + month + "-" + day);
                    }
                });
                picker1.show();
                break;
            case R.id.userhead1:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
                break;
            //TODO 保存信息
            case R.id.save:
                String upSex = sex.getText().toString();
                String sexID = "";
                if (upSex.equals("男")) {
                    sexID = "1";
                } else if (upSex.equals("女")) {
                    sexID = "0";
                }
                saveInformation(mUsername.getText().toString().trim(), date.getText().toString().trim(), sexID);
                break;
            case R.id.informationusername1:
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.settingusername,
                        (ViewGroup) findViewById(R.id.dialog));
                final EditText newName = (EditText) layout.findViewById(R.id.etname);
                new AlertDialog.Builder(this).setTitle("设置用户名").setView(layout)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newNameText = newName.getText().toString().trim();
                                if (TextUtils.isEmpty(newNameText)) {
                                    Toast.makeText(PersonalInformationActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                                } else {
                                    mUsername.setText(newNameText);
                                }

                            }
                        })
                        .setNegativeButton("取消", null).show();

                break;
            case R.id.information_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {

            final Uri uri = data.getData();
            final ContentResolver cr = this.getContentResolver();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(uri, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            final String imagePath = c.getString(columnIndex);
            //TODO 上传头像接口
            Log.i("setting", "参数:" + userID + "  " + imagePath);
            RequestParams params = new RequestParams(HeadUrl);
            params.addBodyParameter("userid", userID);
            params.addBodyParameter("uploadfiel", new File(imagePath));
            params.setMultipart(true);
            x.http().post(params, new Callback.CacheCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject mJson = new JSONObject(result);
                        if (mJson.getString("code").equals("3005")) {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                            userhead.setImageBitmap(bitmap);
                            headerName = mJson.getString("data");
                        } else if (mJson.getString("code").equals("-3005")) {
                            Toast.makeText(PersonalInformationActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("setting", "上传头像:" + result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.i("setting", "上传头像请求错误");
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
        super.onActivityResult(requestCode, resultCode, data);

    }

    //TODO 展示信息
    private void visitInformation() {
        progressDialog = ProgressDialog.show(this, "请稍后", "获取信息中...", true);
        RequestParams params = new RequestParams(URL);
        params.addBodyParameter("userid", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                UserInformationBean information = gson.fromJson(result, UserInformationBean.class);
                if (information.getCode() == 3000) {
                    date.setText(information.getData().getBirthday());
                    Log.i("tag", "性别  " + information.getData().getGender());
                    if (information.getData().getGender().equals("0")) {
                        sex.setText("女");
                    } else if (information.getData().getGender().equals("1")) {
                        sex.setText("男");
                    }
                    mUsername.setText(information.getData().getUsername().toString());
                    headerName = information.getData().getHeader().toString();
                    ImageLoader.getInstance().displayImage(global.getUrl() + headerName, userhead);


                } else if (information.getCode() == -3000) {
                    Toast.makeText(PersonalInformationActivity.this, information.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Toast.makeText(PersonalInformationActivity.this, "服务器异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }

    //TODO 保存修改信息
    private void saveInformation(String uName, String mDate, String mSex) {
        progressDialog = ProgressDialog.show(this, "请稍后", "正在保存...", true);
        RequestParams params = new RequestParams(saveURL);
        params.addBodyParameter("userid", userID);
        params.addBodyParameter("header", headerName);
        params.addBodyParameter("username", uName);
        params.addBodyParameter("gender", mSex);
        params.addBodyParameter("birthday", mDate);
        Log.i("tag", "图片" + headerName + "性别" + mSex);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("mm", result);
                try {
                    JSONObject mJson = new JSONObject(result);
                    Log.i("mm", mJson.get("code").toString());
                    if (mJson.get("code").equals(3000)) {
                        Toast.makeText(PersonalInformationActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (mJson.get("code").equals(-3001)) {
                        Toast.makeText(PersonalInformationActivity.this, "你没做任何修改", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(PersonalInformationActivity.this, "服务器故障", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }

}
