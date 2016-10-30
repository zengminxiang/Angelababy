package com.zmx.angelababy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *作者：胖胖祥
 *时间：2016/8/22 0022 下午 2:36
 *功能模块：
 */
public class SharePreferenceUtil {

    private final String SHAREDPRE_FILE_NAME = "UserMessage"; // 配置文件名

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    /**
     * 单例对象实例
     */
    private static SharePreferenceUtil instance = null;

    public static SharePreferenceUtil getInstance(Context mContext) {
        if (instance == null) {
            synchronized (SharePreferenceUtil.class) {
                if (instance == null) {
                    instance = new SharePreferenceUtil(mContext);
                }
            }
        }
        return instance;
    }

    /**
     * 构造函数
     * @param mContext：上下文环境
     */
    public SharePreferenceUtil(Context mContext) {
        sp = mContext.getSharedPreferences(SHAREDPRE_FILE_NAME,Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    /**
     * 清除本地保存的所有数据
     */
    public void clear(){
        editor.clear();
        editor.commit();
    }

    /**
     * 保存数据到本地
     * @param key 键
     * @param value 值 Object:目前只支持：String/Boolean/Float/Integer/Long
     */
    public void saveKeyObjValue(String key,Object value){
        if(value instanceof String){
            editor.putString(key, (String)value);
        }else if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if(value instanceof Float){
            editor.putFloat(key, (Float) value);
        }else if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if(value instanceof Long){
            editor.putLong(key, (Long) value);
        }
        editor.commit();
    }

    /**
     * 获取保存在本地的数据 未加密的
     * @param key
     * @param defValue
     * @return String
     */
    public String getString(String key, String defValue){
        return sp.getString(key, defValue);
    }
    /**
     * 获取保存在本地的数据 未加密的
     * @param key
     * @param defValue
     * @return Boolean
     */
    public boolean getBoolean(String key, boolean defValue){
        return sp.getBoolean(key, defValue);
    }

    public static String uid = "uid";
    public static String create_time = "create_time";
    public static String user_name = "user_name";
    public static String introduce = "introduce";
    public static String personal_sign = "personal_sign";
    public static String session_id = "session_id";
    public static String tx_id = "tx_id";
    public static String customer = "customer";

}
