package com.zmx.angelababy.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zmx.angelababy.mvp.bean.UserMessageBean;
import com.zmx.angelababy.mvp.model.IDataRequestListener;
import com.zmx.angelababy.mvp.model.UserModel;
import com.zmx.angelababy.mvp.view.LoginView;
import com.zmx.angelababy.mvp.view.RegisterView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *作者：胖胖祥
 *时间：2016/10/24 0024 下午 4:21
 *功能模块：用户处理
 */
public class UserPresenter {

    private UserModel model = new UserModel();
    private RegisterView rv;
    private LoginView lv;
    private Context context;

    public UserPresenter(Context context,RegisterView rv){
        this.context = context;
        this.rv = rv;
    }
    public UserPresenter(Context context,LoginView lv){
        this.context = context;
        this.lv = lv;
    }

    //注册
    public void Register(String user_name, String phone, String pwd, String name, String gender, String en_name, String tx_id, String profession){

        model.Register(user_name, phone, pwd, name, gender, en_name, tx_id, profession, new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {

                Log.e("返回结果：","返回结果："+object.toString());

            }
        });

    }

    public void Login(String user, String pwd){

        model.Login(user, pwd, new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {

                try {
//                    {"ret":false,"msg":"密码不正确"}
                    Log.e("Object","object"+object.toString());

                    JSONObject jsonObject = new JSONObject(object.toString());

                    if(jsonObject.getBoolean("ret")){

                        Gson gson = new Gson();
                        UserMessageBean user = gson.fromJson(jsonObject.toString(),UserMessageBean.class);
                        lv.VLogin(user,jsonObject.getString("msg"));

                    }else{

                        lv.VLogin(null,jsonObject.getString("msg"));

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
