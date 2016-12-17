package com.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


public class Regist_UserTypeActivity extends AppCompatActivity {
    private TextView textView_next;
    private ImageView type_back;
    private RadioButton radioButton_personal, radioButton_store;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist__user_type);
        sharedPreferences = getSharedPreferences("userInformation", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        textView_next = (TextView) findViewById(R.id.next);
        radioButton_personal = (RadioButton) findViewById(R.id.type_personal);
        radioButton_store = (RadioButton) findViewById(R.id.type_store);
        type_back = (ImageView) findViewById(R.id.type_back);
        textView_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton_personal.isChecked()) {
                    editor.putString("groupid","3").apply();
                    Intent p_intent = new Intent(Regist_UserTypeActivity.this, Regist_PersonalActivity.class);
                    startActivity(p_intent);
                } else if (radioButton_store.isChecked()) {
                    editor.putString("groupid","2").apply();
                    Intent s_intent = new Intent(Regist_UserTypeActivity.this, Regist_StoreActivity.class);
                    startActivity(s_intent);
                }
            }
        });
        type_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
