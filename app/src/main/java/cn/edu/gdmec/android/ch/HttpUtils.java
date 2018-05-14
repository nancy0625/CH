package cn.edu.gdmec.android.ch;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Administrator on 2018/5/10.
 */

public class HttpUtils {
    public HttpUtils(){

    }
    public static String getJsonContent(String url_path){
        try {
            URL url = new URL(url_path);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (connection.getResponseCode() == 200){
                return  changInputStream(connection.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
    private static String changInputStream(InputStream inputStream){
        String jsonString="";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        try {
            while ((len = inputStream.read(data))!=-1){
                outputStream.write(data,0,len);
            }
            jsonString = new String(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
