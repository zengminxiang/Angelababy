package com.zmx.angelababy.mvp.model;

/**
 *作者：胖胖祥
 *时间：2016/10/24 0024 下午 4:10
 *功能模块：用户处理
 */
public interface IUserModel {

    /**
     *
     * @param user_name 用户昵称
     * @param phone 手机号
     * @param pwd 密码
     * @param name 姓名
     * @param gender 性别
     * @param en_name 英文名
     * @param tx_id 头像ID
     * @param profession 职业
     */
    void IRegister(String user_name,String phone,String pwd,String name,String gender,String en_name,String tx_id,String profession,IDataRequestListener listener);

    /**
     *
     * @param user 手机号
     * @param pwd 密码
     *
     */
    void ILogin(String user,String pwd,IDataRequestListener listener);


}
