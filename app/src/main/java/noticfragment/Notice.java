package noticfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myproject.R;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class Notice extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.notice_notice,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
