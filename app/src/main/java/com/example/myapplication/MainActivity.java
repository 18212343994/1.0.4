package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    EditText editName,editPSW;

    TextView textView;



    String fileName="cet4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        editName=findViewById(R.id.editName);
        editPSW=findViewById(R.id.editName);

        textView = findViewById(R.id.txt);
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
            textView.setText("用户名："+s[0]+"   密码："+s[1]);
        }
        if(fileInputStream !=null){
            fileInputStream.close();
        }

    }




}
