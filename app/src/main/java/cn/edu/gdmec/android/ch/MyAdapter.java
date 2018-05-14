package cn.edu.gdmec.android.ch;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Map<String ,Object>> testData;
    public MyAdapter(List<Map<String,Object>> testData){
        this.testData = testData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
      viewHolder.img.setImageBitmap((Bitmap)(testData.get(position).get("bitmap")));
        viewHolder.username.setText((String)(testData.get(position).get("username")));
        String age = testData.get(position).get("age").toString();
        viewHolder.age.setText(age+ "岁");
        viewHolder.gender.setText("性别："+(String)testData.get(position).get("gender"));

    }

    @Override
    public int getItemCount() {
        return testData.size();
    }

public static class ViewHolder extends RecyclerView.ViewHolder{
    public ImageView img;
    public TextView username;
    public TextView age;
    public TextView gender;

    public ViewHolder(View itemView) {
        super(itemView);
        username = (TextView)itemView.findViewById(R.id.textView);
        age = (TextView)itemView.findViewById(R.id.textView2);
        gender = (TextView)itemView.findViewById(R.id.textView3);
        img = (ImageView)itemView.findViewById(R.id.imageView);
    }
}
}
