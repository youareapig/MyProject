package helpfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.R;

import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class Ideal extends Fragment{
    private EditText userinput;
    private TextView submit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ideal,container,false);
        userinput= (EditText) view.findViewById(R.id.userinput);
        submit= (TextView) view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentText=userinput.getText().toString().trim();
                if (TextUtils.isEmpty(contentText)){
                    Toast.makeText(getActivity(),"请填写您的意见",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"感谢您的反馈",Toast.LENGTH_SHORT).show();
                    userinput.setText(null);
                }
            }
        });
        return view;
    }



}
