package com.zmx.angelababy.mvp.view;

import com.zmx.angelababy.mvp.bean.GirlMessageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/23.
 */
public interface MainView {

    void VGetGirlMessage(List<GirlMessageBean> lists,boolean no_have);

    void VError();

}
