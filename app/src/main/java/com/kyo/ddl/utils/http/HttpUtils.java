package com.kyo.ddl.utils.http;
/*
                        _ooOoo_
                       o8888888o
                       88" . "88
                       (| -_- |)
                       O\  =  /O
                    ____/`---'\____
                  .'  \\|     |//  `.
                 /  \\|||  :  |||//  \
                /  _||||| -:- |||||-  \
                |   | \\\  -  /// |   |
                | \_|  ''\---/''  |   |
                \  .-\__  `-`  ___/-. /
              ___`. .'  /--.--\  `. . __
           ."" '<  `.___\_<|>_/___.'  >'"".
          | | :  `- \`.;`\ _ /`;.`/ - ` : | |
          \  \ `-.   \_ __\ /__ _/   .-` /  /
     ======`-.____`-.___\_____/___.-`____.-'======
                        `=---='
     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
              佛祖+你强哥保佑       永无BUG
     */

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.kyo.ddl.annotation.KyoAnnotation;
import com.kyo.ddl.model.base.KyoResponseListener;
import com.kyo.ddl.utils.LogUtil;
import com.yolanda.nohttp.BasicBinary;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnUploadListener;
import com.yolanda.nohttp.RedirectHandler;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static final int WHAT_UPLOAD_SINGLE = 0x01;
    //json的提交方式
    public static void sendPost(Context context, final JSONObject json, StringBuilder url, final HttpListener httpListener) {
        final Request<String> request = NoHttp.createStringRequest(url.toString(), RequestMethod.POST);
        LogUtil.e("请求参数", url.toString());
        if (json != null)
            request.setDefineRequestBodyForJson(json);
        HttpCallServer.getRequestInstance().add(context, 0, request,httpListener, false, true);
    }
    //表单提交数据
    public static void sendPostByForm(Context context, final Map<String, String> map, StringBuilder url,HttpListener httpListener) {
        //切记尽量不要使用匿名内部类，会导致内存溢出，最好在外部使用静态的内部类
        final Request<String> request = NoHttp.createStringRequest(url.toString(), RequestMethod.POST);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            LogUtil.e(request.getRequestMethod()+"请求", entry.getKey() + entry.getValue());
            request.add(entry.getKey(), entry.getValue());
        }
        HttpCallServer.getRequestInstance().add(context, 0, request, httpListener, false, true);
    }
    public static void sendGet(final Context context, StringBuilder url, final Map<String, String> map, int what,HttpListener httpListener) {
        final Request<String> request = NoHttp.createStringRequest(url.toString(), RequestMethod.GET);
        LogUtil.e("参数", url.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            request.add(entry.getKey(),entry.getValue());
        }
        HttpCallServer.getRequestInstance().add(context, what, request, httpListener, false, true);
    }
    //上传图片
    public static void uploadImage(Context context, final Map<String, String> map, File file, StringBuilder url,final HttpListener httpListener) {
        final Request<String> request = NoHttp.createStringRequest(url.toString(), RequestMethod.POST);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            LogUtil.e(request.getRequestMethod()+"请求", entry.getKey() + entry.getValue());
            request.add(entry.getKey(), entry.getValue());
        }
        BasicBinary binary=null;
        if(file==null)
            return;
        else
            binary= new FileBinary(file);
        binary.setUploadListener(WHAT_UPLOAD_SINGLE, new OnUploadListener() {
            @Override
            public void onStart(int what) {
                LogUtil.e("开始上传文件", "开始上传文件");
            }

            @Override
            public void onCancel(int what) {
                LogUtil.e("取消上传文件", "取消上传文件");
            }

            @Override
            public void onProgress(int what, int progress) {
                LogUtil.e("上传文件进度", "上传文件进度" + progress);
            }

            @Override
            public void onFinish(int what) {
                LogUtil.e("上传文件完成", "上传文件完成");
            }

            @Override
            public void onError(int what, Exception exception) {
                LogUtil.e("上传文件失败", exception.getMessage());
            }
        });
        request.add("", binary);// 添加1个文件
        HttpCallServer.getRequestInstance().add(context, 0, request, httpListener, false, true);
    }

    //下载图片
//    private void downloadFile(StringBuilder fileUrl, StringBuilder filePath, StringBuilder fileName, Handler handler) throws Exception {
//        try {
//            OutputStream os=null;
//            URL url = new URL(fileUrl.toString());
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            int length = conn.getContentLength();
//            int count = 0;
//            File apkPathFile = new File(filePath.toString());
//            if (!apkPathFile.exists()) {
//                apkPathFile.mkdirs();
//            }
//            File apkFile = new File(filePath.toString(), fileName.toString());
//            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
//            os = new FileOutputStream(apkFile);
//            byte[] buffer = new byte[1024*2];//缓冲大小设置2M
//            do {
//                int numread = in.read(buffer);
//                count += numread;
//                int progress = (int) (((float) count / length) * 100);// 得到当前进度
//                if (progress >= laterate + 1) {// 只有当前进度比上一次进度大于等于1，才可以更新进度
//                    laterate = progress;
//                    Message msg = new Message();
//                    msg.what = 1;
//                    msg.arg1 = progress;
//                    handler.sendMessage(msg);
//                }
//                if (numread <= 0) {// 下载完毕
//                    handler.sendEmptyMessage(2);
//                    canceled = true;
//                    break;
//                }
//                os.write(buffer, 0, numread);
//            } while (!canceled);// 如果没有被取消
//            in.close();
//            os.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.toString();
//            e.printStackTrace();
//        }
//    }

    //重定向
    public static void getRedirectUrl(String url, KyoResponseListener kyoResponseListener) throws MalformedURLException {
        LogUtil.e("访问地址:", url);
        String location = null;
        URL serverUrl = new URL(url);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            //必须设置 false，否则会自动 redirect 到 Location 的地址
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.addRequestProperty("Referer", "http://www.baidu.com/");
            conn.connect();
            location = conn.getHeaderField("Location");
            kyoResponseListener.onSuccess(location);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("跳转地址:", e.getMessage());
            location = "";
            kyoResponseListener.onSuccess(location);
        }
    }

}
