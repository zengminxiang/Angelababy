package com.zmx.angelababy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.zmx.angelababy.BaseFragment;
import com.zmx.angelababy.R;
import com.zmx.angelababy.mvp.bean.GirlMessageBean;
import com.zmx.angelababy.mvp.presenter.MainPresenter;
import com.zmx.angelababy.ui.LoginActivity;
import com.zmx.angelababy.ui.PaymentActivity;
import com.zmx.angelababy.utils.view.XCRoundRectImageView;

/**
 *作者：胖胖祥
 *时间：2016/10/21 0021 上午 10:59
 *功能模块：主页
 */
public class MainFragment extends BaseFragment implements View.OnClickListener{

    private Button chat_button;
    private GirlMessageBean girl;
    private XCRoundRectImageView image;
    private TextView name,desc,message;
    private LinearLayout main_layout;

    private MainPresenter mp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.main_fragment, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected void initView() {

        mp = new MainPresenter(this.getActivity());
        chat_button = (Button) findViewById(R.id.chat_button);
        chat_button.setOnClickListener(this);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        image = (XCRoundRectImageView) findViewById(R.id.image);
        ImageLoader.getInstance().displayImage("http://api.yulincheng.com/upload/get_img.jsp?id="+girl.getTx_id(), image);
        // 设置ratio的值
        image.setRatio(2.43f);
        name = (TextView) findViewById(R.id.name);
        name.setText(girl.getUser_name());
        desc = (TextView) findViewById(R.id.desc);
        desc.setText(girl.getPersonal_sign());
        message = (TextView) findViewById(R.id.message);
        message.setText("职业："+girl.getProfession()+"     "+"年龄：21岁");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.chat_button:

                mp.TelePhone("13751729147","15914502324","60");
//                Intent intent = new Intent(this.getActivity(), PaymentActivity.class);
//                intent.putExtra("girl",girl);
//                startActivity(intent);
                break;

        }

    }

    public void DataGirlMessage(GirlMessageBean girl){

        this.girl = girl;

    }

    public void setLayoutGone(){

        main_layout.setVisibility(View.GONE);

    }
    public void setLayoutVisible(){

        main_layout.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResume() {

        Log.e("111111","111111");
        setLayoutVisible();
        super.onResume();
    }

    @Override
    public void onStop() {
        setLayoutGone();
        Log.e("222222","2222222");
        super.onStop();
    }

}
