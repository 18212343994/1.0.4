package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.adapter.CommonAdapter;
import com.example.myapplication.adapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author vson
 */
public class recite extends AppCompatActivity {
    private String re;
    private String en;
    TextView textView_en;
    private String ran;
    private int cot;

    private RecyclerView rv;
    private CommonAdapter<SwipeCardBean> adapter;
    private List<SwipeCardBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recite);
        textView_en = findViewById(R.id.tvName);
        CardConfig.initConfig(this);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new SwipeCardLayoutManager());

        mDatas = SwipeCardBean.initDatas();

        //Button button = (Button) findViewById(R.id.look);

        adapter = new CommonAdapter<SwipeCardBean>(recite.this, mDatas, R.layout.item_swipe_card) {
            @Override
            public void convert(ViewHolder ViewHolder, SwipeCardBean swipeCardBean) {



                Random rand = new Random();
                int random = rand.nextInt(4257);
                ran =random+ " ";


                requestDate1(ran);
                ViewHolder.setText(R.id.tvName,  en);

                ViewHolder.setText(R.id.tvPrecent, cot++ + "/");
                Glide.with(recite.this)
                        .load(swipeCardBean.getUrl())
                        .into((ImageView) ViewHolder.getView(R.id.iv));

            }
        };
        rv.setAdapter(adapter);

        SwipeCardCallback cardCallback=new SwipeCardCallback(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(cardCallback);
        helper.attachToRecyclerView(rv);
    }

    public void requestDate1(String ran) {

        URL url = null;
        try {
            url = new URL("http://zhapi.free.idcfengye.com/id1?id=" + ran);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)//访问连接
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //textView.setText("sb");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ViewHolder.setText(R.id.tvName, "sb");
                       // Toast.makeText(recite.this, "短点击", Toast.LENGTH_SHORT).show();
                       en="网络sb加载失败，请重试";

                    }
                });
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                re = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        en=re;
                    }
                });
            }
        });
    }




    public void fanyi(View view) throws IOException {
        Toast.makeText(recite.this, "短点击", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //ViewHolder enn = new ViewHolder();
                //ViewHolder.setText(R.id.tvName,  "11");
            }
        }).start();
    }

}
