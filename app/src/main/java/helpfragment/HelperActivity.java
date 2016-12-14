package helpfragment;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myproject.R;

import adpter.Helper_Adapter;

public class HelperActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentManager manager;
    private LinearLayout callphone;
    private TextView phonenumber;
    private RelativeLayout help_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);
        tabLayout= (TabLayout) findViewById(R.id.help_tablatout);
        viewPager= (ViewPager) findViewById(R.id.help_viewpager);
        tabLayout.setupWithViewPager(viewPager);
        callphone= (LinearLayout) findViewById(R.id.callphone);
        phonenumber= (TextView) findViewById(R.id.phonenumber);
        help_back= (RelativeLayout) findViewById(R.id.help_back);
        manager = this.getSupportFragmentManager();
        viewPager.setAdapter(new Helper_Adapter(manager));
        viewPager.setCurrentItem(0);
        callphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phonenumber.getText().toString()));
                startActivity(intent);
            }
        });
        help_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
