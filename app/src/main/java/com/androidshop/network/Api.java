package com.androidshop.network;

import com.androidshop.model.AnswerModel;
import com.androidshop.model.LoginModel;
import com.androidshop.model.QuestionModel;
import com.androidshop.model.RegisterModel;
import com.androidshop.model.UpdateModel;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ${Terry} on 2018/1/30.
 */
public interface Api {

    @POST("register")
    Observable<RegisterModel> register(@QueryMap Map<String, String> map);

    @POST("login")
    Observable<LoginModel> login(@QueryMap Map<String, String> map);

    @POST("forgetPassword")
    Observable<QuestionModel> findPwd(@QueryMap Map<String, String> map);

    @POST("checkAnswer")
    Observable<AnswerModel> checkAnswer(@QueryMap Map<String, String> map);

    @POST("forgotResetPassword")
    Observable<UpdateModel> resetPwd(@QueryMap Map<String, String> map);

}
