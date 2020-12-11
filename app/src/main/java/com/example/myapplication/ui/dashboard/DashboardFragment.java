package com.example.myapplication.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.song.refresh_view.PullToRefreshView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardFragment extends Fragment {
    private ListView mListView;
    private PullToRefreshView mRefreshView;
    private List<String> mData;
    TextView text;
    public TextView textView;
    private ArrayAdapter<String> mAdapter;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textView = root.findViewById(R.id.text_dashboard);

//笑脸刷新
        mRefreshView = (PullToRefreshView) root.findViewById(R.id.refreshView);
        mRefreshView.setColorSchemeColors(Color.RED, Color.BLUE); // 颜色
        mRefreshView.setSmileStrokeWidth(8); // 设置绘制的笑脸的宽度
        mRefreshView.setSmileInterpolator(new LinearInterpolator()); // 笑脸动画转动的插值器
        mRefreshView.setSmileAnimationDuration(2000); // 设置笑脸旋转动画的时长
        //设置下拉刷新监听

        mRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {

                requestData();
            }

            public void requestData() {
                Toast.makeText(getActivity(), "正在刷新，请稍等", Toast.LENGTH_LONG).show();

                mRefreshView.setRefreshing(false);//结束刷新
            }
        });


        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            requestDate1();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }).start();

            }
        });
        return root;

    }


    public void requestDate1() throws IOException {


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
            textView.setText("励志语录：" + "\n" + ret1);

        }
    }


}