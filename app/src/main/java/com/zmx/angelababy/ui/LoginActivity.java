package com.zmx.angelababy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zmx.angelababy.BaseActivity;
import com.zmx.angelababy.MainActivity;
import com.zmx.angelababy.R;
import com.zmx.angelababy.SharePreferenceUtil;
import com.zmx.angelababy.mvp.bean.UserMessageBean;
import com.zmx.angelababy.mvp.presenter.UserPresenter;
import com.zmx.angelababy.mvp.view.LoginView;
import com.zmx.angelababy.utils.MD5Util;

import java.lang.reflect.Field;

/**
 *作者：胖胖祥
 *时间：2016/10/21 0021 下午 4:53
 *功能模块：登录界面
 */
public class LoginActivity extends BaseActivity implements LoginView{

    private View positionView;
    private TextView login_reg,close;//注册跳转
    private Button login_button;
    private EditText login_pwd,login_phone;
    private ProgressBar login_load;

    private UserPresenter up;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

        positionView = findViewById(R.id.position_view);
        dealStatusBar(positionView); // 调整状态栏高度

        up = new UserPresenter(this,this);

        login_reg = (TextView) findViewById(R.id.login_reg);
        login_reg.setOnClickListener(this);
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_phone = (EditText) findViewById(R.id.login_phone);

        login_load= (ProgressBar) findViewById(R.id.login_load);
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){

            //注册
            case R.id.login_reg:
                startActivity(RegisterActivity.class);
                break;
            //登录
            case R.id.login_button:

                login_load.setVisibility(View.VISIBLE);
                up.Login(login_phone.getText().toString(), MD5Util.encrypt(login_pwd.getText().toString()));

                break;

            case R.id.close:
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                onBackPressed();
                break;

        }

    }

    @Override
    public void VLogin(UserMessageBean user,String msg) {

        if(user != null){

            Log.e("user","user"+user.toString());

            SharePreferenceUtil.getInstance(mActivity).saveKeyObjValue(SharePreferenceUtil.uid,user.getUid());
            SharePreferenceUtil.getInstance(mActivity).saveKeyObjValue(SharePreferenceUtil.create_time,user.getCreate_time());
            SharePreferenceUtil.getInstance(mActivity).saveKeyObjValue(SharePreferenceUtil.user_name,user.getUser_name());
            SharePreferenceUtil.getInstance(mActivity).saveKeyObjValue(SharePreferenceUtil.introduce,user.getIntroduce());
            SharePreferenceUtil.getInstance(mActivity).saveKeyObjValue(SharePreferenceUtil.personal_sign,user.getPersonal_sign());
            SharePreferenceUtil.getInstance(mActivity).saveKeyObjValue(SharePreferenceUtil.session_id,user.getSession_id());
            SharePreferenceUtil.getInstance(mActivity).saveKeyObjValue(SharePreferenceUtil.tx_id,user.getTx_id());
            SharePreferenceUtil.getInstance(mActivity).saveKeyObjValue(SharePreferenceUtil.customer,user.getCustomer());

            Intent intent = new Intent(this, MainActivity.class);
            setResult(1, intent);
            finish();
        }else{

            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();

        }

        login_load.setVisibility(View.GONE);

    }
}
