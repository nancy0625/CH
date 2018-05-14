package cn.edu.gdmec.android.ch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button button;
    private Handler handler;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1){
                    List<Map<String,Object>> testData = (List<Map<String,Object>>)msg.obj;
                    adapter = new MyAdapter(testData);
                    recyclerView.setAdapter(adapter);
                }
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://192.168.191.1/ch5/";
                String jsonString = HttpUtils.getJsonContent(path+"selectjson.php");
                Log.i("jsonString",jsonString);
                List<Map<String,Object>> testData = GsonTools.getList(jsonString);
                Log.i("TTT",testData+"");
                for (Map<String, Object> map : testData){
                    try {
                        URL url = new URL(path + "images/"+(String)(map.get("image")));
                        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                        InputStream is = conn.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        map.put("bitmap",bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    message.what = 1;
                    message.obj = testData;
                    handler.sendMessage(message);
                }
            }
        });
        thread.start();
    }
}
