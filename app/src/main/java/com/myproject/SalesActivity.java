package com.myproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SalesActivity extends AppCompatActivity {
    @BindView(R.id.salesback)
    RelativeLayout salesback;
    @BindView(R.id.salesviewgorup)
    LinearLayout salesviewgorup;
    @BindView(R.id.salesgridview)
    GridView salesgridview;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        unbinder = ButterKnife.bind(this);
        salesback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();

    }
}
