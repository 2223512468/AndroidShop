package com.androidshop.network;

import com.androidshop.model.AnswerModel;
import com.androidshop.model.FilterModel;
import com.androidshop.model.ItemModel;
import com.androidshop.model.LoginModel;
import com.androidshop.model.LoginOutModel;
import com.androidshop.model.QuestionModel;
import com.androidshop.model.RegisterModel;
import com.androidshop.model.UpdateModel;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ${Terry} on 2018/1/30.
 */
public interface Api {

    @POST("user/register")
    Observable<RegisterModel> register(@Body RequestBody requestBody);

    @POST("user/login")
    Observable<LoginModel> login(@Body RequestBody requestBody);

    @POST("user/forgetPassword")
    Observable<QuestionModel> findPwd(@QueryMap Map<String, String> map);

    @POST("user/checkAnswer")
    Observable<AnswerModel> checkAnswer(@QueryMap Map<String, String> map);

    @POST("user/forgotResetPassword")
    Observable<UpdateModel> resetPwd(@QueryMap Map<String, String> map);

    @POST("user/logout")
    Observable<LoginOutModel> logout(@QueryMap Map<String, String> map);

    @Multipart
    @POST("user/updateInformation")
    Observable<LoginModel> updateInformation(@Part("json") RequestBody requestBody, @Part("session") RequestBody requestBody1);

    @POST("user/getUserInfo")
    Observable<LoginOutModel> getUserInfo(@QueryMap Map<String, String> map);

    @POST("item/item")
    Observable<ItemModel> config(@Body RequestBody requestBody);

    @POST("item/filter")
    Observable<FilterModel> filter();

}
