package com.zmx.angelababy.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zmx.angelababy.BaseActivity;
import com.zmx.angelababy.R;

public class SetupActivity extends BaseActivity {

    private TextView close;
    private View positionView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setup;
    }

    @Override
    protected void initViews() {

        positionView = findViewById(R.id.position_view);
        dealStatusBar(positionView); // 调整状态栏高度
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(this);

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

        }


    }
}
