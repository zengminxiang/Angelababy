package com.zmx.angelababy.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zmx.angelababy.mvp.bean.GirlMessageBean;
import com.zmx.angelababy.mvp.model.IDataRequestListener;
import com.zmx.angelababy.mvp.model.MainModel;
import com.zmx.angelababy.mvp.view.MainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/22.
 */
public class MainPresenter {

    private MainModel model = new MainModel();
    private MainView mv;
    private Context context;

    public MainPresenter(Context context, MainView mv) {

        this.context = context;
        this.mv = mv;

    }

    public MainPresenter(Context context){
        this.context = context;
    }

    public void getGrilMessageList(String begin, String plen) {

        Log.e("getGrilMessageList", "进来了");

        model.GetGirlMessage(begin, plen, new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {

                String state = object.toString();

                if (state.equals("error")) {
                    mv.VError();
                } else {

                    try {

                        JSONObject json = new JSONObject(object.toString());

                        boolean no_have = json.getBoolean("no_have");

                        List<GirlMessageBean> lists = new ArrayList<GirlMessageBean>();
                        Gson gson = new Gson();
                        JSONArray array = new JSONArray(json.getString("list"));
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject jsonObject = (JSONObject) array.get(i);
                            GirlMessageBean gmb = gson.fromJson(jsonObject.toString(), GirlMessageBean.class);
                            lists.add(gmb);

                        }

                        mv.VGetGirlMessage(lists, no_have);

                    } catch (JSONException e) {
                        e.printStackTrace();

                        Log.e("getGrilMessageList", "异常");
                        mv.VError();

                    }

                }
            }
        });

    }

    public void TelePhone(String from_phone, String to_phone, String talk_time){

        model.TelePhone(from_phone, to_phone, talk_time, new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {

                Log.e("拨打电话","拨打："+object);

            }
        });

    }

}
