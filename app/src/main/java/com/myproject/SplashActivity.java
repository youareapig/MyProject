package com.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private ImageView splashImage;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        splashImage = (ImageView) findViewById(R.id.spalsh);
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(1000);
        splashImage.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                        flag=sharedPreferences.getString("flag",null);
                            if (flag.equals("first")){
                                Intent intent = new Intent();
                                intent.setClass(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            Intent intent = new Intent();
                            intent.setClass(SplashActivity.this, LeaderActivity.class);
                            editor.putString("flag", "first").apply();
                            startActivity(intent);
                            finish();
                        }

                    }
                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}