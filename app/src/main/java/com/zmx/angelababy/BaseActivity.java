package com.zmx.angelababy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Field;


public abstract class BaseActivity extends AutoLayoutActivity implements View.OnClickListener {

    protected Activity mActivity;


    /**
     * 跳转到下一个activity
     **/
    protected static final int REQUEST_ACTIVITY = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
        mActivity = this;
        // 1. 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //找到资源文件的XML
        if (getLayoutId() != 0) {

            View vContent = LayoutInflater.from(mActivity).inflate(getLayoutId(), null);
            ((FrameLayout) findViewById(R.id.frame_content)).addView(vContent);

        }
        initViews();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

//            case R.id.back:
//
//                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                onBackPressed();
//
//                break;

        }

    }

    /**
     * 初始化Activity的常用变量 举例:
     * <b>mLayoutResID=页面XML资源ID 必须存在的</b>
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图
     **/
    protected abstract void initViews();

    /**
     * 通过Class跳转界面
     *
     * @param cls
     */
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     *
     * @param cls
     * @param bundle
     */
    protected void startActivity(Class<?> cls, Bundle bundle) {
        startActivity(cls, bundle, REQUEST_ACTIVITY);
    }

    /**
     * 通过Class跳转界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    protected void startActivity(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 调整沉浸式菜单的title
     */
    protected void dealStatusBar(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = getStatusBarHeight();
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.height = statusBarHeight;
            view.setLayoutParams(lp);
        }
    }

    private int getStatusBarHeight() {

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

}

