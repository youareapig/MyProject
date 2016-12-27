package utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.ailunwang.appupdate.service.UpdateService;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import myview.CustomDialog;

/**
 * Created by Administrator on 2016/12/27.
 */
public class UpdateVersion {
    public static void checkVersion(final Context context, final int locationVersion){
        final RequestParams params = new RequestParams("https://www.ailunwang.cn/version.xml");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                HashMap<String, String> hashMap = new HashMap<String, String>();
                Document document = null;
                // 实例化一个文档构建器工厂
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                // 通过文档构建器工厂获取一个文档构建器
                DocumentBuilder builder = null;
                try {
                    builder = factory.newDocumentBuilder();

                    document = builder.parse(new ByteArrayInputStream(result.getBytes()));
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                // 通过文档通过文档构建器构建一个文档实例
                 catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //获取XML文件根节点
                Element root = document.getDocumentElement();
                //获得所有子节点
                NodeList childNodes = root.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++)
                {
                    //遍历子节点
                    Node childNode = (Node) childNodes.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element childElement = (Element) childNode;
                        //版本号
                        if ("version".equals(childElement.getNodeName()))
                        {
                            hashMap.put("version",childElement.getFirstChild().getNodeValue());
                        }
                        //软件名称
                        else if (("name".equals(childElement.getNodeName())))
                        {
                            hashMap.put("name",childElement.getFirstChild().getNodeValue());
                        }
                        //下载地址
                        else if (("url".equals(childElement.getNodeName())))
                        {
                            hashMap.put("url",childElement.getFirstChild().getNodeValue());
                        }
                    }
                }
                if (Integer.parseInt(hashMap.get("version"))>locationVersion){
                    CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
                    builder1.setTitle("升级提示");
                    builder1.setMessage("发现新版本，请及时更新");
                    builder1.setPositiveButton("立即升级", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(context, UpdateService.class);
                            intent.putExtra("apkUrl", "https://www.ailunwang.cn/Android/ALAndroid.apk");
                            context.startService(intent);
                        }
                    });
                    builder1.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    builder1.create().show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
