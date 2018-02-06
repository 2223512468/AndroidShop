package com.androidshop.network;

import com.androidshop.model.AnswerModel;
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

    @POST("register")
    Observable<RegisterModel> register(@Body RequestBody requestBody);

    @POST("login")
    Observable<LoginModel> login(@Body RequestBody requestBody);

    @POST("forgetPassword")
    Observable<QuestionModel> findPwd(@QueryMap Map<String, String> map);

    @POST("checkAnswer")
    Observable<AnswerModel> checkAnswer(@QueryMap Map<String, String> map);

    @POST("forgotResetPassword")
    Observable<UpdateModel> resetPwd(@QueryMap Map<String, String> map);

    @POST("logout")
    Observable<LoginOutModel> logout(@QueryMap Map<String, String> map);

    @Multipart
    @POST("updateInformation")
    Observable<LoginModel> updateInformation(@Part("json") RequestBody requestBody, @Part("session") RequestBody requestBody1);

    @POST("getUserInfo")
    Observable<LoginOutModel> getUserInfo(@QueryMap Map<String, String> map);

}
