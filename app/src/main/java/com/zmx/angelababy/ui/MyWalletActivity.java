package com.zmx.angelababy.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zmx.angelababy.BaseActivity;
import com.zmx.angelababy.R;
/**
 *作者：胖胖祥
 *时间：2016/10/25 0025 下午 6:14
 *功能模块：我的钱包
 */
public class MyWalletActivity extends BaseActivity {

    private View positionView;
    private TextView close,message;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void initViews() {
        positionView = findViewById(R.id.position_view);
        dealStatusBar(positionView); // 调整状态栏高度
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(this);
        message = (TextView) findViewById(R.id.message);
        message.setVisibility(View.VISIBLE);
        message.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){

            case R.id.close:
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                onBackPressed();

                break;

            case R.id.message:

                startActivity(MyWalletMessageAcitivity.class);

                break;

        }

    }
}
