package com.zmx.angelababy.mvp.model;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zmx.angelababy.MyApplication;
import com.zmx.angelababy.utils.VolleyPostRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *作者：胖胖祥
 *时间：2016/10/24 0024 下午 4:14
 *功能模块：用户处理
 */
public class UserModel implements IUserModel{



    public void Register(String user_name, String phone, String pwd, String name, String gender, String en_name, String tx_id, String profession, IDataRequestListener listener) {

        IRegister(user_name,phone,pwd,name,gender,en_name,tx_id,profession,listener);

    }

    public void Login(String user, String pwd,final IDataRequestListener listener){
        ILogin(user,pwd,listener);
    }


    @Override
    public void IRegister(String user_name, String phone, String pwd, String name, String gender, String en_name, String tx_id, String profession,final IDataRequestListener listener) {

        Map<String,String> map = new HashMap<>();
        map.put("user_name",user_name);
        map.put("phone",phone);
        map.put("pwd",pwd);
        map.put("name",name);
        map.put("gender",gender);
        map.put("en_name",en_name);
        map.put("tx_id",tx_id);
        map.put("profession",profession);

        VolleyPostRequest jor = new VolleyPostRequest("http://api.yulincheng.com/voice/user/user_register.jsp", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                listener.loadSuccess(jsonObject);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e("加载错误", "加载错误");

            }
        }, map);

        jor.setTag("jor");
        MyApplication.getHttpQueues().add(jor);
        jor.setShouldCache(true); // 控制是否缓存

    }

    @Override
    public void ILogin(String user, String pwd,final IDataRequestListener listener) {

        Map<String,String> map = new HashMap<>();
        map.put("user",user);
        map.put("pwd",pwd);

        VolleyPostRequest jor = new VolleyPostRequest("http://api.yulincheng.com/voice/user/user_login.jsp", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                listener.loadSuccess(jsonObject);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e("加载错误", "加载错误");

            }
        }, map);

        jor.setTag("jor");
        MyApplication.getHttpQueues().add(jor);
        jor.setShouldCache(true); // 控制是否缓存

    }


}
