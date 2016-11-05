package com.zmx.angelababy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zmx.angelababy.BaseActivity;
import com.zmx.angelababy.R;
import com.zmx.angelababy.mvp.presenter.UserPresenter;
import com.zmx.angelababy.mvp.view.RegisterView;
import com.zmx.angelababy.utils.MD5Util;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class VerificationActivithy extends BaseActivity implements RegisterView{

    private View positionView;
    private TextView verification_phone,close;
    private EditText verification_code;
    private Button again_get,submit;
    private ProgressBar loading;
    private LinearLayout layout;

    private String phone,pwd,name;

    private EventHandler eh;

    private int time = 60;// 发送验证码的时间
    private boolean state = true;//停止线程

    private UserPresenter up;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verification;
    }

    @Override
    protected void initViews() {

        positionView = findViewById(R.id.position_view);
        dealStatusBar(positionView); // 调整状态栏高度

        up = new UserPresenter(this,this);

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        pwd = intent.getStringExtra("pwd");
        name = intent.getStringExtra("name");

        verification_phone = (TextView) findViewById(R.id.verification_phone);
        verification_phone.setText(phone);
        layout = (LinearLayout) findViewById(R.id.verification_layout);
        loading = (ProgressBar) findViewById(R.id.login_load);
        again_get = (Button) findViewById(R.id.again_get);
        again_get.setOnClickListener(this);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        verification_code = (EditText) findViewById(R.id.verification_code);
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(this);

        //mob
        eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                //回调完成
                if (result == SMSSDK.RESULT_COMPLETE) {

                    //提交验证码成功,和验证成功返回
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {

                        //提交资料注册
                        handler.sendEmptyMessage(SUCCESS);

                    }

                    //获取验证码成功
                    else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {

                        handler.sendEmptyMessage(MARK_ONE);


                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {

                        //返回支持发送验证码的国家列表

                    }
                } else {

                    ((Throwable) data).printStackTrace();
                    handler.sendEmptyMessage(FAIL);

                }
            }
        };

        SMSSDK.registerEventHandler(eh); //注册短信回调

        SMSSDK.getVerificationCode("86", phone);//发送验证码

    }

    private final int MARK_ONE = 1;
    private final int BUTTON_FALSE = 2;//修改button验证码
    private final int BUTTON_TRUE = 3;//修改button验证码
    private final int SUCCESS = 4;//验证码验证成功
    private final int FAIL = 5;//验证码验证失败

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case MARK_ONE:

                    layout.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);
                    reGetCode();
                    break;

                case BUTTON_FALSE:

//                    again_get.setBackgroundResource(R.color.white);
                    again_get.setClickable(false);
                    again_get.setText(time + "秒");

                    break;

                case BUTTON_TRUE:

                    again_get.setText("获取验证码");
                    again_get.setClickable(true);
                    break;

                case SUCCESS:

                    //提交资料注册
                    up.Register("胖胖祥龙",phone, MD5Util.encrypt(pwd),"曾敏祥","","","","");

                    break;

                case FAIL:
                    loading.setVisibility(View.GONE);
                    Toast.makeText(mActivity,"验证码错误",Toast.LENGTH_LONG).show();
                    break;

            }

        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){

            //提交按钮
            case R.id.submit:

                //判断是否输入为空
                if (TextUtils.isEmpty(verification_code.getText().toString())) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                loading.setVisibility(View.VISIBLE);
                SMSSDK.submitVerificationCode("86", phone, verification_code.getText().toString());//验证验证码

                break;

            //重新获取验证码按钮
            case R.id.again_get:


                loading.setVisibility(View.VISIBLE);
                SMSSDK.getVerificationCode("86", phone);

                break;

            case R.id.close:
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                onBackPressed();
                break;

        }
    }

    //等待60秒重新获取验证码
    public void reGetCode() {

        new Thread() {

            @Override
            public void run() {
                super.run();

                while (state) {

                    try {

                        if (time == 1) {

                            handler.sendEmptyMessage(BUTTON_TRUE);
                            time = 60;
                            break;

                        }

                        time--;
                        handler.sendEmptyMessage(BUTTON_FALSE);

                        Thread.sleep(1000);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

            }
        }.start();

    }


    @Override
    public void Register(boolean success, String uid) {

        loading.setVisibility(View.GONE);

        if(success){

            Toast.makeText(this,"注册成功,请登录",Toast.LENGTH_LONG).show();
            this.finish();

        }else{

            Toast.makeText(this,uid,Toast.LENGTH_LONG).show();
        }

    }
}
