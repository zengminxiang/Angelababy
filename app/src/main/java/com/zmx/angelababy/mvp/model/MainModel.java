package com.zmx.angelababy.mvp.model;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.zmx.angelababy.MyApplication;
import com.zmx.angelababy.utils.VolleyPostRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/22.
 */
public class MainModel implements IMainModel{


    //获取女郎的信息
    public void GetGirlMessage(String begin, String plen,IDataRequestListener listener){
        IGetGirlMessage(begin,plen,listener);

    }


    @Override
    public void IGetGirlMessage(String begin, String plen, final IDataRequestListener listener) {

        Map<String,String> map = new HashMap<>();
        map.put("begin",begin);
        map.put("plen",plen);

        VolleyPostRequest jor = new VolleyPostRequest("http://api.yulincheng.com/voice/user/user_girl_list.jsp", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                listener.loadSuccess(jsonObject);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                listener.loadSuccess("error");

            }
        }, map);

        jor.setTag("jor");
        MyApplication.getHttpQueues().add(jor);
        jor.setShouldCache(true); // 控制是否缓存


    }


}
