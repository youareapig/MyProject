package indexfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myproject.GarageActivity;
import com.myproject.MaintainRecordActivity;
import com.myproject.OrderActivity;
import com.myproject.R;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Personal extends Fragment implements View.OnClickListener{
    private TextView personal_allorder;
    private RelativeLayout wait_pay,wait_receive,wait_install,wait_anzhuang,order_cargarage,order_maintain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.personal,container,false);
        personal_allorder= (TextView) view.findViewById(R.id.personal_allorder);
        wait_pay= (RelativeLayout) view.findViewById(R.id.waitpay);
        wait_receive= (RelativeLayout) view.findViewById(R.id.waitreceive);
        wait_install= (RelativeLayout) view.findViewById(R.id.waitinstall);
        wait_anzhuang= (RelativeLayout) view.findViewById(R.id.waitevaluate);
        order_cargarage= (RelativeLayout) view.findViewById(R.id.order_cargarage);
        order_maintain= (RelativeLayout) view.findViewById(R.id.order_maintain);
        personal_allorder.setOnClickListener(this);
        wait_pay.setOnClickListener(this);
        wait_receive.setOnClickListener(this);
        wait_install.setOnClickListener(this);
        wait_anzhuang.setOnClickListener(this);
        order_cargarage.setOnClickListener(this);
        order_maintain.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personal_allorder:
                Intent intent=new Intent(getActivity(),OrderActivity.class);
                intent.putExtra("title",personal_allorder.getText().toString());
                startActivity(intent);
                break;
            case R.id.waitpay:
                Intent intent1=new Intent(getActivity(),OrderActivity.class);
                intent1.putExtra("title","待付款");
                startActivity(intent1);
                break;
            case R.id.waitreceive:
                Intent intent2=new Intent(getActivity(),OrderActivity.class);
                intent2.putExtra("title","待收货");
                startActivity(intent2);
                break;
            case R.id.waitinstall:
                Intent intent3=new Intent(getActivity(),OrderActivity.class);
                intent3.putExtra("title","待安装");
                startActivity(intent3);
                break;
            case R.id.waitevaluate:
                Intent intent4=new Intent(getActivity(),OrderActivity.class);
                intent4.putExtra("title","待评价");
                startActivity(intent4);
                break;
            //TODO 我的车库
            case R.id.order_cargarage:
                Intent intent5=new Intent(getActivity(), GarageActivity.class);
                startActivity(intent5);
                break;
            //TODO 保养记录
            case R.id.order_maintain:
                Intent intent6=new Intent(getActivity(), MaintainRecordActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
