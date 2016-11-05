package com.zmx.angelababy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.zmx.angelababy.fragment.MainFragment;
import com.zmx.angelababy.mvp.bean.GirlMessageBean;

import java.util.List;

/**
 *作者：胖胖祥
 *时间：2016/10/21 0021 下午 4:46
 *功能模块：viewPager滑动适配器
 */
public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<MainFragment> lists;
    private List<GirlMessageBean> gmbs;
    private MainFragment mainFragment;

    public MainFragmentPagerAdapter(FragmentManager fm, List<MainFragment> lists,List<GirlMessageBean> gmbs){
        super(fm);
        this.lists = lists;
        this.gmbs = gmbs;

    }

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.e("position","position"+position);
        MainFragment mf = lists.get(position);
        mf.DataGirlMessage(gmbs.get(position));
        return mf;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mainFragment = (MainFragment) object;
        super.setPrimaryItem(container, position, object);
    }


}
