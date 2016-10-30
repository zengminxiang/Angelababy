package com.zmx.angelababy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zmx.angelababy.BaseActivity;
import com.zmx.angelababy.R;
import com.zmx.angelababy.mvp.bean.GirlMessageBean;
import com.zmx.angelababy.utils.view.ImageViewUtil;

/**
 *作者：胖胖祥
 *时间：2016/10/25 0025 下午 3:29
 *功能模块：支付界面
 */
public class PaymentActivity extends BaseActivity {

    private View positionView;
    private GirlMessageBean girl;
    private ImageViewUtil head;
    private TextView name,desc,message;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initViews() {

        girl = (GirlMessageBean) getIntent().getSerializableExtra("girl");

        positionView = findViewById(R.id.position_view);
        dealStatusBar(positionView); // 调整状态栏高度

        head = (ImageViewUtil) findViewById(R.id.head);
        ImageLoader.getInstance().displayImage("http://api.yulincheng.com/upload/get_img.jsp?id="+girl.getTx_id(), head);
        name = (TextView) findViewById(R.id.name);
        name.setText(girl.getUser_name());
        desc = (TextView) findViewById(R.id.desc);
        desc.setText(girl.getPersonal_sign());
        message = (TextView) findViewById(R.id.message);
        message.setText("职业："+girl.getProfession()+"     "+"年龄：21岁");


    }
}
