package utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.util.Xml;

import com.ailunwang.appupdate.service.UpdateService;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import myview.CustomDialog;

/**
 * Created by Administrator on 2016/12/27.
 */
public class UpdateVersion {
    HashMap<String, String> hashMap = new HashMap<String, String>();

    public static HashMap<String, String> checkVersion(final Context context, final int locationVersion) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String url = "http://www.ailunwang.cn/version.xml";
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream in = conn.getInputStream();
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(in, "UTF-8");
                int event = parser.getEventType();
                Log.v("88888888888", "----------------------->成功");
                while (event != XmlPullParser.END_DOCUMENT) {
                    Log.i("start_document", "start_document");
                    switch (event) {
                        case XmlPullParser.START_TAG:
                            if ("version".equals(parser.getName())) {
                                String version = parser.nextText();
                                hashMap.put("version", version);
                            }
                            if (("name").equals(parser.getName())) {
                                String version_name = parser.nextText();
                                hashMap.put("name", version_name);
                            }

                            if ("url".equals(parser.getName())) {
                                String updateUrl = parser.nextText();
                                hashMap.put("url", updateUrl);
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                    }
                    event = parser.next();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }


}
