package com.androidshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidshop.model.LoginModel;
import com.google.gson.Gson;

/**
 * 类描述：
 * 创建人：admin
 * 创建时间：2016/5/10 16:14
 * 修改人：admin
 * 修改时间：2016/5/10 16:14
 * 修改备注：
 */
public class LoginUtil {

    private static String SP_NAME="shop";
    private static String KEY="BAILINGTONG";

    /**
     * 保存登录信息
     * @param mContext
     * @param info
     */
    public static void saveInfo(Context mContext,LoginModel info){
        Gson gson=new Gson();
        String gsonInfo=gson.toJson(info);
        gsonInfo=AESUtils.encode(gsonInfo);
        savaUser(mContext,gsonInfo);
    }
    /**
     * 获取登录信息
     * @param mContext
     */
    public static LoginModel getInfo(Context mContext){
        Gson gson=new Gson();
        String gsonInfo=getInfoStr(mContext);
        if(StringUtil.isEmpty(gsonInfo))return null;
        return gson.fromJson(gsonInfo,LoginModel.class);
    }

    /**
     * 是否登录
     * @param context context
     * @return 是否登录
     */
    public static boolean isLogin(Context context){
        return null!=getInfo(context);
    }
    /**
     * 清空登录信息
     * @param mContext
     */
    public static void clearInfo(Context mContext){
        SharedPreferences sp=mContext.getApplicationContext().getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
    private static void savaUser(Context mContext,String info){
        SharedPreferences sp=mContext.getApplicationContext().getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        try {
            sp.edit().putString(KEY,info).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getInfoStr(Context mContext){
        SharedPreferences sp=mContext.getApplicationContext().getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        String info=sp.getString(KEY,"");
        if(!StringUtil.isEmpty(info)){
            try {
                return AESUtils.decode(info);
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }

}
