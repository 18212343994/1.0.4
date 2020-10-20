package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//1.0.2
public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    EditText editName,editPSW;
    private long mExitTime;
    TextView textView;



    String fileName="cet4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        editName=findViewById(R.id.editName);
        editPSW=findViewById(R.id.editName);

        textView = findViewById(R.id.txt);


        final Button fanyi=(Button)findViewById(R.id.fanyi);
        final Button add=(Button)findViewById(R.id.add);

        final Button btn1=(Button)findViewById(R.id.btn1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,add.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,cet4.class);
                startActivity(intent);
            }
        });

        fanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,fanyi.class);
                startActivity(intent);
            }
        });

    }

    public void btn1Click(View view) throws IOException {
        String name=editName.getText().toString();
        String pwd=editPSW.getText().toString();

        FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
        fileOutputStream.write((name+"##"+pwd).getBytes());

        if(fileOutputStream !=null){
            fileOutputStream.close();
        }

        Toast.makeText(this,"ok",Toast.LENGTH_LONG).show();
    }

    public void btn2Click(View view) throws IOException {
        FileInputStream fileInputStream = openFileInput(fileName);
        byte[] input = new byte[fileInputStream.available()];
        while(fileInputStream.read(input) != -1){
            String str = new String(input);
            String [] s = str.split("##");
            textView.setText("英文："+s[0]+"   中文："+s[1]);
        }
        if(fileInputStream !=null){
            fileInputStream.close();
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }






    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出APP", Toast.LENGTH_SHORT).show();
                //System.currentTimeMillis()系统当前时间
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
