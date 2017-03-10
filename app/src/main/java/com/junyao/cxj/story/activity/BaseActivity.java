package com.junyao.cxj.story.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junyao.cxj.story.R;


public class BaseActivity extends AppCompatActivity {

    ImageView img_left;
    ImageView img_right;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.content);
        //将传入的layout加载到activity_base的relativeLayout
        getLayoutInflater().inflate(layoutResID,relativeLayout,true);
        super.setContentView(view);
        initView();
    }

    protected void initView() {
        img_left = (ImageView) findViewById(R.id.img_left);
        img_right = (ImageView) findViewById(R.id.img_right);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }
    public void setTv_title(String title) {
        tv_title.setText(title);
    }
    public void setLeftVisible(boolean isVisible) {
        img_left.setVisibility((isVisible == true) ? View.VISIBLE : View.GONE);
    }
    public void setRightVisible(boolean isVisible) {
        img_right.setVisibility((isVisible == true) ? View.VISIBLE : View.GONE);
    }

    public void back(View view) {
        finish();
    }
}
