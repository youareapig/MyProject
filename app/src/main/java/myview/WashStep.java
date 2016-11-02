package myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myproject.R;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class WashStep extends RelativeLayout {
    private ImageView imageView;
    private TextView textView;
    public WashStep(Context context) {
        super(context);
        init(context);
    }

    public WashStep(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);

    }
    public void init(Context context){
        View view=inflate(context, R.layout.washstep,this);
        imageView= (ImageView) view.findViewById(R.id.wash_step_img);
        textView= (TextView) view.findViewById(R.id.wash_step_text);
    }
    public void setImageView(int img){
        imageView.setImageResource(img);
    }
    public void setTextView(String string){
        textView.setText(string);
    }
}
