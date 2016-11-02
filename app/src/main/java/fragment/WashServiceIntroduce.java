package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myproject.R;

import myview.WashStep;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class WashServiceIntroduce extends Fragment {
    private WashStep step1, step2, step3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.washservice_introduce, container, false);
        step1 = (WashStep) view.findViewById(R.id.step1);
        step2 = (WashStep) view.findViewById(R.id.step2);
        step3 = (WashStep) view.findViewById(R.id.step3);

        step1.setTextView("步骤1：洗车洗车洗车洗车洗车");
        step1.setImageView(R.mipmap.washstep);
        step2.setTextView("步骤1：洗车洗车洗车洗车洗车");
        step2.setImageView(R.mipmap.washstep);
        step3.setTextView("步骤1：洗车洗车洗车洗车洗车");
        step3.setImageView(R.mipmap.washstep);

        return view;
    }
}
