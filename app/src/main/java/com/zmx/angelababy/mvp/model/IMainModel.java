package com.zmx.angelababy.mvp.model;

/**
 * Created by Administrator on 2016/10/22.
 */
public interface IMainModel {

    /**
     * 获取女郎信息
     * @param begin 查询的页码数
     * @param plen 查询多少条
     */
    void IGetGirlMessage(String begin,String plen,IDataRequestListener listener);

    void ITelePhone(String from_phone,String to_phone,String talk_time,IDataRequestListener listener);

}
