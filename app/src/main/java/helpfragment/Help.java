package helpfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myproject.R;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import utils.Global;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class Help extends Fragment{
    private WebView webView;
    private String url;
    private Global global;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.help,container,false);
        global=new Global();
        url=global.getUrl()+"/api.php/News/question";
        webView= (WebView) view.findViewById(R.id.help_web);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
        return view;
    }

}
