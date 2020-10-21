package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.SplittableRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Random;
//1.0.2
public class cet4 extends AppCompatActivity {
    Button cet4;
    Button btn4;
    TextView textView;
    String re;
    public int ran = 1;
    String fileName="cet4";
    public String chinese;
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cet4);
        btn4 = findViewById(R.id.btn4);
        textView = findViewById(R.id.txt);

        Button button=(Button)findViewById(R.id.close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void btn1Click(View view) throws IOException {
        //Button btn = new Button(context);




        new Thread(new Runnable() {
            @Override
            public void run() {

                requestDate();
            }
        }).start();


    }




    public void show(View view){

        new Thread(new Runnable() {
            @Override
            public void run() {
                requestDate1();
            }
        }).start();
    }

















    public void requestDate(){
        //textView.setText("sb");


        Random rand = new Random();


        int random=rand.nextInt(4257);
        ran=random;






        URL url = null;
        try {
            url = new URL("http://ef5c3b.natappfree.cc/id?id="+random);
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
                        textView.setText("sb");

                    }
                });

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                re = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //String en = "chinese\":(.+?),";
                       // Matcher matcher = Pattern.compile(en).matcher(re);
                        //while (matcher.find()){
                            //String ret = matcher.group(1);
                        btn4.setText("下一个");
                            textView.setText(re);

                       // }


                    }
                });
               //textView.setText("sb");
//
//                String chinese = "chinese\":(.+?),";
//                Matcher ch = Pattern.compile(chinese).matcher(re);
//
//                while (ch.find()){
//                    String chi = ch.group(1);

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //textView.setText(re);
//                            String en = "english\":(.+?),";
//                            //String zh = "chinese\":(.+?),";
//                            Matcher matcher = Pattern.compile(en).matcher(re);
//                            //Matcher matcher1 = Pattern.compile(zh).matcher(re);
//                            while (matcher.find()){
//                                String ret = matcher.group(1);
//                                //String ret1 = matcher1.group(1);
//                                textView.setText(re);
//                            }
//
////                            while (matcher1.find()){
////                                String ret1 = matcher.group(0);
////                                chinese = ret1;
////                                //textView.setText(ret);
////                            }
//
//                        }
//                    });



                //}
                //textView.setText(re);
//                textView.setText(re);


            }
        });












    }

















    //以下是内存储模式下的单词读取
//    public void btn2Click(View view) throws IOException {
//
//        count++;
//
//        FileInputStream fileInputStream = openFileInput(fileName);
//        byte[] input = new byte[fileInputStream.available()];
//        while(fileInputStream.read(input) != -1){
//            String str = new String(input);
//            String [] s = str.split("##");
//
//            if(count%2==0){
//
//                textView.setText("英文：" + s[count] + "   中文：" + s[count + 1]);
//            }else {
//
//                textView.setText("英文：" + s[count+1]);
//                //textView.setText("英文：" + s[0] + "   中文：" + s[1]);
//            }
//        }
//
//
//        if(fileInputStream !=null){
//            fileInputStream.close();
//        }
//
//    }









    public void requestDate1(){
        //textView.setText("sb");









        URL url = null;
        try {
            url = new URL("http://ef5c3b.natappfree.cc/id1?id="+ran);
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
                        textView.setText("sb");

                    }
                });

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                re = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //String en = "chinese\":(.+?),";
                        // Matcher matcher = Pattern.compile(en).matcher(re);
                        //while (matcher.find()){
                        //String ret = matcher.group(1);
                        textView.setText(re);
                        // }


                    }
                });
                //textView.setText("sb");
//
//                String chinese = "chinese\":(.+?),";
//                Matcher ch = Pattern.compile(chinese).matcher(re);
//
//                while (ch.find()){
//                    String chi = ch.group(1);

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //textView.setText(re);
//                            String en = "english\":(.+?),";
//                            //String zh = "chinese\":(.+?),";
//                            Matcher matcher = Pattern.compile(en).matcher(re);
//                            //Matcher matcher1 = Pattern.compile(zh).matcher(re);
//                            while (matcher.find()){
//                                String ret = matcher.group(1);
//                                //String ret1 = matcher1.group(1);
//                                textView.setText(re);
//                            }
//
////                            while (matcher1.find()){
////                                String ret1 = matcher.group(0);
////                                chinese = ret1;
////                                //textView.setText(ret);
////                            }
//
//                        }
//                    });



                //}
                //textView.setText(re);
//                textView.setText(re);


            }
        });












    }




}
