package com.myproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RetrievePasswordActivity extends AppCompatActivity {
    @BindView(R.id.retrievrpasswordactivity_back)
    ImageView mBack;
    @BindView(R.id.retrievrpasswordactivity_telephone)
    EditText mTelephone;
    @BindView(R.id.retrievrpasswordactivity_vercode)
    EditText mVercode;
    @BindView(R.id.retrievrpasswordactivity_getvercode)
    TextView mGetvercode;
    @BindView(R.id.retrievrpasswordactivity_userpassword)
    EditText mRetrieverUserpassword;
    @BindView(R.id.retrievrpasswordactivity_repeatuserpassword)
    EditText mUserpassword;
    @BindView(R.id.retrievrpasswordactivity_complete)
    TextView mComplete;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.retrievrpasswordactivity_getvercode, R.id.retrievrpasswordactivity_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.retrievrpasswordactivity_getvercode:
                break;
            case R.id.retrievrpasswordactivity_complete:
                break;
        }
    }
}
