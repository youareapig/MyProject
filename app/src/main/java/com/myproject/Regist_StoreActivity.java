package com.myproject;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import bean.UpLoadingPhoto;
import utils.Global;

public class Regist_StoreActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView store_back, permit;
    private TextView store_finish, upload;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText company, company_addr, company_number, company_scope;
    private String permitName;
    private ProgressDialog progressDialog = null;
    private String mUrl,mUrl2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist__store);
        Global global=new Global();
        mUrl=global.getUrl()+"api.php/Member/uploadfiel";
        mUrl2=global.getUrl()+"api.php/Member/registAdd";
        sharedPreferences = getSharedPreferences("userInformation", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        store_back = (ImageView) findViewById(R.id.store_back);
        store_finish = (TextView) findViewById(R.id.store_finish);
        permit = (ImageView) findViewById(R.id.permit);
        upload = (TextView) findViewById(R.id.upload);
        company = (EditText) findViewById(R.id.company);
        company_addr = (EditText) findViewById(R.id.company_addr);
        company_number = (EditText) findViewById(R.id.company_number);
        company_scope = (EditText) findViewById(R.id.company_scope);
        store_back.setOnClickListener(this);
        store_finish.setOnClickListener(this);
        permit.setOnClickListener(this);
        upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store_back:
                finish();
                break;
            case R.id.store_finish:
                String mCompany = company.getText().toString();
                String mCompany_addr = company_addr.getText().toString();
                String mCompany_number = company_number.getText().toString();
                String mCompany_scope = company_scope.getText().toString();
                String phone = sharedPreferences.getString("mobile", null);
                String password = sharedPreferences.getString("password", null);
                String usertype = sharedPreferences.getString("groupid", null);
                if (TextUtils.isEmpty(mCompany) || TextUtils.isEmpty(mCompany_addr) || TextUtils.isEmpty(mCompany_number) || TextUtils.isEmpty(mCompany_scope) || TextUtils.isEmpty(permitName)) {
                    Toast.makeText(Regist_StoreActivity.this, "请完善店铺信息", Toast.LENGTH_SHORT).show();
                } else {
                    visit(phone, password, usertype, mCompany, mCompany_addr, mCompany_number, permitName, mCompany_scope);
                }
                break;
            case R.id.upload:
//                AlertDialog.Builder builder = new AlertDialog.Builder(Regist_StoreActivity.this);
//                LayoutInflater inflater = this.getLayoutInflater();
//                View view = inflater.inflate(R.layout.dialog, null);
//                view.findViewById(R.id.dialog_photo).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        choose();
//
//                    }
//                });
//                view.findViewById(R.id.dialog_camera).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        camera();
//                    }
//                });
//                builder.setView(view);
//                builder.show();
                choose();
                break;
        }
    }

    private void choose() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

//    private void camera() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 2);
//    }

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
            Log.i("path", "图片路径" + imagePath + "   " + columnIndex);

            //TODO 上传图片到服务器
            progressDialog = ProgressDialog.show(this, "请稍后", "上传中....", true);
            RequestParams params = new RequestParams(mUrl);
            params.addBodyParameter("uploadedfile", new File(imagePath));
            params.setMultipart(true);
            x.http().post(params, new Callback.CacheCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    UpLoadingPhoto upLoadingPhoto = gson.fromJson(result, UpLoadingPhoto.class);
                    permitName = upLoadingPhoto.getData();
                    if (upLoadingPhoto.getCode() == 3005) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                            permit.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(Regist_StoreActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Toast.makeText(Regist_StoreActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
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
        if (requestCode == 2) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                return;
            }
            String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            FileOutputStream b = null;
            File file = new File("/sdcard/myImage/");
            file.mkdirs();
            String fileName = "/sdcard/myImage/" + name;

            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    b.close();
                    b.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            permit.setImageBitmap(bitmap);

        }
    }

    private void visit(final String phone, String pwd, String userType, String company, String company_addr, String company_number, String company_pic, String company_scope) {
        RequestParams params = new RequestParams(mUrl2);
        params.addBodyParameter("mobile", phone);
        params.addBodyParameter("password", pwd);
        params.addBodyParameter("groupid", userType);
        params.addBodyParameter("company", company);
        params.addBodyParameter("company_addr", company_addr);
        params.addBodyParameter("company_number", company_number);
        params.addBodyParameter("company_pic", company_pic);
        params.addBodyParameter("company_scope", company_scope);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject  jsonObject = new JSONObject(result);
                    String mCode=jsonObject.getString("code");
                    if (mCode.equals("3000")){
                        Intent intent=new Intent(Regist_StoreActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("tel",phone);
                        startActivity(intent);

                    }else if (mCode.equals("-3003")){
                        Toast.makeText(Regist_StoreActivity.this,"该手机号已经被注册",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(Regist_StoreActivity.this,"请求超时",Toast.LENGTH_SHORT).show();
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
