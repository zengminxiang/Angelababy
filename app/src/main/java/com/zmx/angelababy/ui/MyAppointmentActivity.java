package com.zmx.angelababy.ui;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zmx.angelababy.BaseActivity;
import com.zmx.angelababy.R;
import com.zmx.angelababy.fragment.AppointmentFragment.AllAppointmentFragment;
import com.zmx.angelababy.fragment.AppointmentFragment.AlreadyGiveFragment;
import com.zmx.angelababy.fragment.AppointmentFragment.AlreadyServiceFragment;
import com.zmx.angelababy.fragment.AppointmentFragment.StayServiceFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *作者：胖胖祥
 *时间：2016/10/25 0025 上午 9:48
 *功能模块：我的预约
 */
public class MyAppointmentActivity extends BaseActivity {

    private View positionView;
    private Context context = this;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private List<Fragment> list_fragment;  //tablayout的标题
    private AllAppointmentFragment aaf;
    private AlreadyGiveFragment agf;
    private AlreadyServiceFragment asf;
    private StayServiceFragment ssf;
    private String[] mTitles = new String[]{"全部","待服务","已服务","已赠送"};


    private TextView close;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_appointment;
    }

    @Override
    protected void initViews() {

        positionView = findViewById(R.id.position_view);
        dealStatusBar(positionView); // 调整状态栏高度

        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.appointment_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.appointment_tabs);
        aaf = new AllAppointmentFragment();
        agf = new AlreadyGiveFragment();
        asf = new AlreadyServiceFragment();
        ssf = new StayServiceFragment();
        list_fragment = new ArrayList<>();
        list_fragment.add(aaf);
        list_fragment.add(agf);
        list_fragment.add(asf);
        list_fragment.add(ssf);
        /*viewPager通过适配器与fragment关联*/
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list_fragment.get(position);
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        //TabLayout和ViewPager的关联
        tabLayout.setupWithViewPager(mViewPager);


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
