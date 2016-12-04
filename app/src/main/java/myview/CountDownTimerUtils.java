package myview;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.myproject.R;

/**
 * Created by Administrator on 2016/12/4 0004.
 */
public class CountDownTimerUtils extends CountDownTimer{
    private TextView mTextView;
    /**
     *60s后获取验证码
     */
    public CountDownTimerUtils(TextView mTextView,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView=mTextView;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "秒重新发送");  //设置倒计时时间
        mTextView.setTextSize(12);
        mTextView.setBackgroundResource(R.drawable.btn_login_down); //设置按钮为灰色，这时是不能点击的
        SpannableString spannableString = new SpannableString(mTextView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.btn_login_up);  //还原背景色
    }
}
