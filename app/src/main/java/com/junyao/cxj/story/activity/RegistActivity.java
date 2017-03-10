package com.junyao.cxj.story.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.junyao.cxj.story.R;
import com.junyao.cxj.story.application.MyApplication;

import java.io.File;

public class RegistActivity extends BaseActivity {

    private static final int REQUEST_CODE = 1;
    private static final int ALL_PHOTO = 2;
    private static final int RESULT_PHOTO = 3;
    private EditText nikename;
    private EditText account;
    private EditText password;
    private ImageView img_head;
    private View v;
    private LinearLayout local_img;
    private LinearLayout take_photo;
    private Button btn_cancel;
    private String head_url;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        setRightVisible(false);
        setTv_title("用户注册");
        findViewId();
        initListener();
    }

    /**
     * 设置监听
     */
    private void initListener() {
        account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {//获得焦点
                    Toast.makeText(RegistActivity.this, "输入内容不可为中文", Toast.LENGTH_SHORT).show();
                } else {//失去焦点
                    if (TextUtils.isEmpty(account.getText().toString())) {
                        Toast.makeText(RegistActivity.this, "输入内容不可为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        nikename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(nikename.getText().toString())) {
                        Toast.makeText(RegistActivity.this, "输入内容不可为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(password.getText().toString())) {
                        Toast.makeText(RegistActivity.this, "输入内容不可为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //监测虚拟键盘登录
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromInputMethod(
                            getWindow().getDecorView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    regist(textView);
                    return true;
                }
                return false;
            }
        });
        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.popu_window, null);
                final PopupWindow popupWindow = new PopupWindow(v, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                //获取屏幕宽度
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                popupWindow.setWidth(dm.widthPixels);
//                popupWindow.setAnimationStyle(R.style.popuwindow);
                //显示位置
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                popupWindow.setOnDismissListener(new poponDismissListener());
                local_img = (LinearLayout) v.findViewById(R.id.layout_localimg);
                take_photo = (LinearLayout) v.findViewById(R.id.layout_takephoto);
                btn_cancel = (Button) v.findViewById(R.id.btn_cancel);
                //点击取消按钮
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        backgroundAlpha(1f);
                        popupWindow.dismiss();
                    }
                });
                //点击本地相册按钮
                local_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        backgroundAlpha(1f);
                        popupWindow.dismiss();
                        //调用手机相册的方法
                        allPhoto();
                    }
                });
                //点击拍照按钮
                take_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        backgroundAlpha(1f);
                        popupWindow.dismiss();
                        //调用手机照相机的方法,通过Intent调用系统相机完成拍照，并调用系统裁剪器裁剪照片
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //创建文件路径，头像保存的路径
                        head_url = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"mydownloads" + File.separator + "head.jpg";
                        file = new File(head_url);
                        //保存图片到Intent中，并通过Intent将照片传给系统裁剪器
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(head_url)));
                        //图片质量
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                        //启动有返回的Intent，即返回裁剪好的图片到RoundImageView组件中显示
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //如果返回码不为-1，则表示不成功
        if (resultCode != RESULT_OK){
            return;
        }
        if (requestCode == ALL_PHOTO){
            //调用相册
            Cursor cursor = this.getContentResolver().query(data.getData(),
                    new String[]{MediaStore.Images.Media.DATA},null,null,null);
            //游标移到第一位，即从第一位开始读取
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            //调用系统裁剪
            startPhoneZoom(Uri.fromFile(new File(path)));
        }else if (requestCode == REQUEST_CODE){
            //相机返回结果，调用系统裁剪
            startPhoneZoom(Uri.fromFile(new File(head_url)));
        }else if(requestCode == RESULT_PHOTO) {
            //设置裁剪返回的位图
            Bundle bundle = data.getExtras();
            if (bundle!=null){
                Bitmap bitmap = bundle.getParcelable("data");
                //将裁剪后得到的位图在组件中显示
                img_head.setImageBitmap(bitmap);
            }
        }
    }
    //调用系统裁剪的方法
    private void startPhoneZoom(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //是否可裁剪
        intent.putExtra("corp", "true");
        //裁剪器高宽比
        intent.putExtra("aspectY",1);
        intent.putExtra("aspectX",1);
        //设置裁剪框高宽
        intent.putExtra("outputX",150);
        intent.putExtra("outputY", 150);
        //返回数据
        intent.putExtra("return-data",true);
        startActivityForResult(intent,RESULT_PHOTO);
    }

    //调用手机相册
    private void allPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,ALL_PHOTO);
    }
    /**
     * 添加PopupWindow关闭的事件，主要是为了将背景透明度改回来
     *
     */
    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 找id
     */
    private void findViewId() {
        nikename = (EditText) findViewById(R.id.et_nickname);
        account = (EditText) findViewById(R.id.et_account);
        password = (EditText) findViewById(R.id.et_password);
        img_head = (ImageView) findViewById(R.id.img_head);

    }

    /**
     * 点击注册按钮监听
     *
     * @param view
     */
    public void regist(View view) {
        getData();
    }

    /**
     * 注册网络请求
     */
    public void getData() {
        String username = this.account.getText().toString();
        String nikename = this.nikename.getText().toString();
        String password = this.password.getText().toString();
        //HttpUtils.getRegistData(nikename, username, password, file,this);
    }

}
