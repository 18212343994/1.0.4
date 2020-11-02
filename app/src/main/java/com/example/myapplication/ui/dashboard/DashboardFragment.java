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

import java.util.List;

public class DashboardFragment extends Fragment {
    private ListView mListView;
    private PullToRefreshView mRefreshView;
    private List<String> mData;
    private ArrayAdapter<String> mAdapter;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);

//笑脸刷新
        mRefreshView = (PullToRefreshView) root.findViewById(R.id.refreshView);
        mRefreshView.setColorSchemeColors(Color.RED,Color.BLUE); // 颜色
        mRefreshView.setSmileStrokeWidth(8); // 设置绘制的笑脸的宽度
        mRefreshView.setSmileInterpolator(new LinearInterpolator()); // 笑脸动画转动的插值器
        mRefreshView.setSmileAnimationDuration(2000); // 设置笑脸旋转动画的时长
        //设置下拉刷新监听
        mRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
            private void requestData() {
                Toast.makeText(getActivity(),"正在刷新，请稍等",Toast.LENGTH_LONG).show();
                mRefreshView.setRefreshing(false);//结束刷新
            }
        });



        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

}