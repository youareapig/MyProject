package indexfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myproject.AboutActivity;
import com.myproject.GarageActivity;
import com.myproject.MainActivity;
import com.myproject.MaintainRecordActivity;
import com.myproject.OrderActivity;
import com.myproject.R;
import com.myproject.SettingActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bean.UserInformationBean;
import de.hdodenhof.circleimageview.CircleImageView;
import helpfragment.HelperActivity;
import myview.CustomDialog;
import utils.Global;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Personal extends Fragment implements View.OnClickListener {
    private TextView personal_allorder, sign_out;
    private RelativeLayout wait_pay, wait_receive, wait_install, wait_anzhuang, order_cargarage, order_maintain, help, about, setting;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String userID, mUrl;
    private CircleImageView personal_head;
    private Global global;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal, container, false);

        sharedPreferences = getActivity().getSharedPreferences("userLogin", getActivity().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userID = sharedPreferences.getString("userID", null);
        global = new Global();
        mUrl = global.getUrl() + "api.php/Member/person";

        personal_allorder = (TextView) view.findViewById(R.id.personal_allorder);
        sign_out = (TextView) view.findViewById(R.id.sign_out);
        wait_pay = (RelativeLayout) view.findViewById(R.id.waitpay);
        wait_receive = (RelativeLayout) view.findViewById(R.id.waitreceive);
        wait_install = (RelativeLayout) view.findViewById(R.id.waitinstall);
        wait_anzhuang = (RelativeLayout) view.findViewById(R.id.waitevaluate);
        order_cargarage = (RelativeLayout) view.findViewById(R.id.order_cargarage);
        order_maintain = (RelativeLayout) view.findViewById(R.id.order_maintain);
        personal_head = (CircleImageView) view.findViewById(R.id.personal_head);
        help = (RelativeLayout) view.findViewById(R.id.help);
        about = (RelativeLayout) view.findViewById(R.id.about);
        setting = (RelativeLayout) view.findViewById(R.id.setting);

        help.setOnClickListener(this);
        about.setOnClickListener(this);
        setting.setOnClickListener(this);
        sign_out.setOnClickListener(this);
        personal_allorder.setOnClickListener(this);
        wait_pay.setOnClickListener(this);
        wait_receive.setOnClickListener(this);
        wait_install.setOnClickListener(this);
        wait_anzhuang.setOnClickListener(this);
        order_cargarage.setOnClickListener(this);
        order_maintain.setOnClickListener(this);
        getHeader();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_allorder:
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                editor.putString("title", personal_allorder.getText().toString()).apply();
                editor.putString("ordertype", "all").apply();
                startActivity(intent);
                break;
            case R.id.waitpay:
                Intent intent1 = new Intent(getActivity(), OrderActivity.class);
                editor.putString("title", "待付款").apply();
                editor.putString("ordertype", "0").apply();
                startActivity(intent1);
                break;
            case R.id.waitreceive:
                Intent intent2 = new Intent(getActivity(), OrderActivity.class);
                editor.putString("title", "待收货").apply();
                editor.putString("ordertype", "1").apply();
                startActivity(intent2);
                break;
            case R.id.waitinstall:
                Intent intent3 = new Intent(getActivity(), OrderActivity.class);
                editor.putString("title", "已签收").apply();
                editor.putString("ordertype", "2").apply();
                startActivity(intent3);
                break;
            case R.id.waitevaluate:
                Intent intent4 = new Intent(getActivity(), OrderActivity.class);
                editor.putString("title", "待评价").apply();
                editor.putString("ordertype", "4").apply();
                startActivity(intent4);
                break;
            //TODO 我的车库
            case R.id.order_cargarage:
                Intent intent5 = new Intent(getActivity(), GarageActivity.class);
                startActivity(intent5);
                break;
            //TODO 保养记录
            case R.id.order_maintain:
                Intent intent6 = new Intent(getActivity(), MaintainRecordActivity.class);
                startActivity(intent6);
                break;
            case R.id.help:
                Intent intent7 = new Intent(getActivity(), HelperActivity.class);
                startActivity(intent7);
                break;
            case R.id.about:
                Intent intent8 = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent8);
                break;
            case R.id.setting:
                Intent intent9 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent9);
                break;
            case R.id.sign_out:
                /**
                 * 登录存入状态值1，注销存入2
                 * */
                CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("确定退出登录吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putString("state", "2").apply();
                        editor.putString("groupID","0").apply();
                        Intent mIntent = new Intent(getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(mIntent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                break;

        }
    }

    //TODO 获取用户头像
    private void getHeader() {
        RequestParams params = new RequestParams(mUrl);
        params.addBodyParameter("userid", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                UserInformationBean information = gson.fromJson(result, UserInformationBean.class);
                if (information.getCode() == 3000) {
                    ImageLoader.getInstance().displayImage(global.getUrl() + information.getData().getHeader(), personal_head);

                } else if (information.getCode() == -3000) {
                    Toast.makeText(getActivity(), information.getMessage(), Toast.LENGTH_SHORT).show();
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
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
