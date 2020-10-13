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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//1.0.2
public class cet4 extends AppCompatActivity {
    Button cet4;
    TextView textView;
    String fileName="cet4";
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cet4);
        textView = findViewById(R.id.txt);

        Button button=(Button)findViewById(R.id.close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void btn2Click(View view) throws IOException {

        count++;

        FileInputStream fileInputStream = openFileInput(fileName);
        byte[] input = new byte[fileInputStream.available()];
        while(fileInputStream.read(input) != -1){
            String str = new String(input);
            String [] s = str.split("##");
            textView.setText("英文：" + s[count] + "   中文：" + s[count+1]);
                //textView.setText("英文：" + s[0] + "   中文：" + s[1]);
        }


        if(fileInputStream !=null){
            fileInputStream.close();
        }

    }




}
