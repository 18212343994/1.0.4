package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class mydict extends Activity {
    String re="翻译结果：";

    Button fanyi;
    EditText input;
    TextView textView;
    public static final String Tag = fanyi.class.getCanonicalName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fanyi);
        fanyi=findViewById(R.id.fanyi);
        input=findViewById(R.id.input);
        textView = findViewById(R.id.txt);




        //setContentView(R.layout.fanyi);
        Button button=(Button)findViewById(R.id.close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void fanyiOnclick(View view) throws IOException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                requestDate();
            }
        }).start();
//        requestDate();

        // Retrofit + Rxjava

        //String pwd=input.getText().toString();


//        URL u = new URL("http://translate.google.cn/translate_a/single?client=gtx&dt=t&dj=1&ie=UTF-8&sl=auto&tl=zh&q="+pwd);
//        InputStream in = u.openStream();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        try {
//            byte buf[] = new byte[1024];
//            int read = 0;
//            while ((read = in.read(buf)) > 0) {
//                out.write(buf, 0, read);
//            }
//        } finally {
//            if ( in != null) {
//                in.close();
//            }
//        }
//
//        byte b[] = out.toByteArray();
//        String a=new String(b, "utf-8");
//        String chinese = "trans\":(.+?),";
//        Matcher ch = Pattern.compile(chinese).matcher(a);
//        while (ch.find()){
//
//            String chi = ch.group(1);
//            textView.setText(chi);
//
//        }




        //textView.setText(pwd);

        Toast.makeText(this,"翻译成功",Toast.LENGTH_LONG).show();


    }






    //使用okhttp框架
    private void requestDate() {

        String pwd=input.getText().toString();
        URL url = null;
        try {
            url = new URL("http://translate.google.cn/translate_a/single?client=gtx&dt=t&dj=1&ie=UTF-8&sl=auto&tl=zh&q="+pwd);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //String url = "http://translate.google.cn/translate_a/single?client=gtx&dt=t&dj=1&ie=UTF-8&sl=auto&tl=zh&q=test";
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
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                re = response.body().string();


                String chinese = "trans\":(.+?),";
                Matcher ch = Pattern.compile(chinese).matcher(re);

                while (ch.find()){



                    String chi = ch.group(1);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(chi);


                        }
                    });



                }
                //textView.setText(re);
                //textView.setText("hrll");

            }
        });




    }}















