package com.junyao.cxj.story.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.junyao.cxj.story.R;


public class LoginActivity extends BaseActivity {

    private EditText et_username;
    private EditText et_password;
    private TextView tv_regist;
    private Button btn_login;
    private ImageView img_qq;
    private ImageView img_sina;
    private ImageView img_weichat;
    //private Platform qq = ShareSDK.getPlatform(QQ.NAME);
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        et_username.setText(username);
        et_password.setText(password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRightVisible(false);
        findViewId();
        initListener();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        //获取焦点监听
        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(et_password.getText().toString())) {
                        Toast.makeText(LoginActivity.this,"输入内容不可为空",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(et_password.getText().toString())) {
                        Toast.makeText(LoginActivity.this,"输入内容不可为空",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //监测虚拟键盘登录
        et_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromInputMethod(
                            getWindow().getDecorView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    login(btn_login);
                    return true;
                }
                return false;
            }
        });
        //点击注册按钮，没有账号，则点击跳到注册界面
        tv_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
            }
        });
        /*//QQ登录
        img_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qq.SSOSetting(false);//默认为false，表示使用sso授权
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        String userId = platform.getDb().getUserId();
                        if (userId != null) {
                            *//*Message message = new Message();
                            int what = message.what;

                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();*//*
                            handler.sendEmptyMessage(0);
                        }
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                qq.showUser(null);//授权并获取用户信息
            }
        });*/
    }

    /**
     * 找id
     */
    private void findViewId() {
        et_username = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_regist = (TextView) findViewById(R.id.tv_regist);
        btn_login = (Button) findViewById(R.id.btn_longin);
        /*img_qq = (ImageView) findViewById(R.id.img_qq);
        img_sina = (ImageView) findViewById(R.id.img_sina);
        img_weichat = (ImageView) findViewById(R.id.img_weixin);*/
    }

    /**
     * 点击登录按钮，登录成功则跳到主界面
     * @param view
     */
    public void login(View view) {
        getData();
    }

    /**
     * 获得数据
     */
    public void getData() {
        String name = et_username.getText().toString();
        String pwd = et_password.getText().toString();
        Log.e("====", "name=" + name + "=password=" + pwd);
        //HttpUtils.getLoginData(name, pwd, this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (qq.isAuthValid()) {
            qq.removeAccount(true);
        }*/
    }
}
