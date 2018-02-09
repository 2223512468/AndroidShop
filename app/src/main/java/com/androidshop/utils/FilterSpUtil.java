package com.androidshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidshop.model.FilterModel;
import com.google.gson.Gson;

/**
 * 类描述：
 * 创建人：admin
 * 创建时间：2016/5/10 16:14
 * 修改人：admin
 * 修改时间：2016/5/10 16:14
 * 修改备注：
 */
public class FilterSpUtil {

    private static String SP_NAME="JAJAHOME_FILTER";
    private static String KEY="FILTER";

    /**
     * 保存登录信息
     * @param mContext
     * @param info
     */
    public static void saveModel(Context mContext,FilterModel info){
        Gson gson=new Gson();
        String gsonInfo=gson.toJson(info);
        save(mContext,gsonInfo);
    }
    /**
     * 获取登录信息
     * @param mContext
     */
    public static FilterModel getInfo(Context mContext){
        Gson gson=new Gson();
        String gsonInfo=getInfoStr(mContext);
        if(StringUtil.isEmpty(gsonInfo))return null;
        return gson.fromJson(gsonInfo,FilterModel.class);
    }

    /**
     * 清空登录信息
     * @param mContext
     */
    public static void clear(Context mContext){
        SharedPreferences sp=mContext.getApplicationContext().getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
    private static void save(Context mContext,String info){
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
        return info;
    }

}
