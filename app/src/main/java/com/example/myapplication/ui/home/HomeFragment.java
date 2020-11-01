package com.example.myapplication.ui.home;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.cet4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class HomeFragment extends Fragment {
    ViewPager mViewpage;
    LinearLayout mLayout;
    ArrayList<ImageView> dots = new ArrayList<>();
    ArrayList<Integer> imgs = new ArrayList<>();
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

    //自动切换轮播图代码
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            handler.sendEmptyMessageDelayed(1,2000);
            mViewpage.setCurrentItem(mViewpage.getCurrentItem()+1);
            super.handleMessage(msg);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //test=(Button)root.findViewById(R.id.test);


        view = inflater.inflate(R.layout.fragment_home, null);
        //final TextView textView = root.findViewById(R.id.text_home);
        final TextView txt = root.findViewById(R.id.txt);
        mViewpage = root.findViewById(R.id.viewpager1);
        mLayout = root.findViewById(R.id.layout);
        imgs.add(R.drawable.e);
        imgs.add(R.drawable.a);
        imgs.add(R.drawable.b);
        imgs.add(R.drawable.c);
        imgs.add(R.drawable.d);
        imgs.add(R.drawable.e);
        imgs.add(R.drawable.a);

        for (int i=0;i<imgs.size()-2;i++){
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30,30);
            params.leftMargin = 5;
            params.rightMargin = 5;
            imageView.setLayoutParams(params);
            dots.add(imageView);
            mLayout.addView(imageView);
        }
        //textView = textView.findViewById();
        mViewpage.setAdapter(new MyAdapter());
        mViewpage.setCurrentItem(2);
        setDostImgs();
        handler.sendEmptyMessage(1);
        mViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    mViewpage.setCurrentItem(imgs.size()-2,false);

                }
                if (position ==imgs.size()-1){
                    mViewpage.setCurrentItem(1,false);

                }
                setDostImgs();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


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


                        //textView.setText("装修中......敬请期待");
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


    public void setDostImgs(){
        for (int i=0 ;i<dots.size();i++){
            if (i == mViewpage.getCurrentItem()-1){
                dots.get(i).setBackgroundResource(R.drawable.yes);
            }else {
                dots.get(i).setBackgroundResource(R.drawable.no);
            }
        }
    }


    //csdn轮播图
    public class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imgs.get(position));
            container.addView(imageView);
            return imageView;
        }
    }


}

