package com.zmx.angelababy.ui;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zmx.angelababy.BaseActivity;
import com.zmx.angelababy.R;

import java.lang.reflect.Field;

public class RegisterActivity extends BaseActivity {

    private View positionView;
    private EditText register_phone,register_pwd,register_name;
    private Button register_Button;
    private TextView close;

//    用户昵称、手机号、密码、姓名、性别、英文名、头像ID、职业

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {

        positionView = findViewById(R.id.position_view);
        dealStatusBar(positionView); // 调整状态栏高度

        register_phone = (EditText) findViewById(R.id.register_phone);
        register_pwd = (EditText) findViewById(R.id.register_pwd);
        register_name = (EditText) findViewById(R.id.register_name);
        register_Button = (Button) findViewById(R.id.register_Button);
        register_Button.setOnClickListener(this);
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){

            case R.id.register_Button:

                if(TextUtils.isEmpty(register_phone.getText().toString())){
                    Toast.makeText(this,"请输入手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(register_pwd.getText().toString())){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(register_name.getText().toString())){
                    Toast.makeText(this,"请输入昵称",Toast.LENGTH_LONG).show();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("phone",register_phone.getText().toString());
                bundle.putString("pwd",register_pwd.getText().toString());
                bundle.putString("name",register_name.getText().toString());

                startActivity(VerificationActivithy.class,bundle);
                this.finish();

                break;

            case R.id.close:
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                onBackPressed();
                break;


        }

    }




}
