package com.zmx.angelababy.fragment.AppointmentFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmx.angelababy.BaseFragment;
import com.zmx.angelababy.R;

/**
 *作者：胖胖祥
 *时间：2016/10/25 0025 下午 5:45
 *功能模块：
 */
public class AllAppointmentFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_all_app, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);

    }


    @Override
    protected void initView() {

    }
}
