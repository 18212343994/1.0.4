package com.example.myapplication.ui.home;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.cet4;
import com.example.myapplication.fanyi;

public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private String[] contentDescs;
    private TextView tv_desc;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;
    public int ran = 1;
    private Button test;
    Button btn1, btn2, fanyi,mydict;
    EditText editName, editPSW;
    private long mExitTime;
    TextView text;
    private View view, view2;


    String fileName = "cet4";
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //test=(Button)root.findViewById(R.id.test);
        view = inflater.inflate(R.layout.fragment_home, null);
        final TextView textView = root.findViewById(R.id.text_home);
        final TextView txt = root.findViewById(R.id.txt);


        //textView = textView.findViewById();


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override

            public void onChanged(@Nullable String s) {

//                textView.setText("HELLO");


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            requestDate();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    private void requestDate() throws IOException {


                        textView.setText("装修中......敬请期待");
                        URL u = new URL("http://route.showapi.com/1211-1?showapi_appid=391443&count=1&showapi_sign=ad54ea4fa4b444e0b360c35847261ffc");
                        InputStream in = u.openStream();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        try {
                            byte buf[] = new byte[1024];
                            int read = 0;
                            while ((read = in.read(buf)) > 0) {
                                out.write(buf, 0, read);
                            }
                        } finally {
                            if (in != null) {
                                in.close();
                            }
                        }
                        byte b[] = out.toByteArray();
                        System.out.println(new String(b, "utf-8"));
                        String a = new String(b, "utf-8");

                        String regex = "english\":(.+?)。";
                        Matcher matcher = Pattern.compile(regex).matcher(a);
                        while (matcher.find()) {
                            String ret = matcher.group(0);
                            String ret1 = ret.replace("\",\"", "\n");
                            txt.setText("励志语录：" + "\n" + ret1);

                        }
                    }
                }).start();

            }
        });
        return root;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        btn1 = (Button) getActivity().findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), cet4.class);
                startActivity(intent);

            }
        });


        fanyi = (Button) getActivity().findViewById(R.id.fanyi);
        fanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), com.example.myapplication.fanyi.class);
                startActivity(intent);
            }
        });



        mydict = (Button) getActivity().findViewById(R.id.mydict);
        mydict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), com.example.myapplication.mydict.class);
                startActivity(intent);
            }
        });


    }


}

