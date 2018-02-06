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
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ${Terry} on 2018/1/30.
 */
public class ApiImp implements Api {

    private static ApiImp mApiImp;

    public static ApiImp get() {
        if (null == mApiImp) {
            mApiImp = new ApiImp();
        }
        return mApiImp;
    }

    private Api genApi() {
        return HttpService.instance().getApi();
    }


    @Override
    public Observable<RegisterModel> register(@Body RequestBody requestBody) {
        return genApi().register(requestBody);
    }

    @Override
    public Observable<LoginModel> login(@Body RequestBody requestBody) {
        return genApi().login(requestBody);
    }

    @Override
    public Observable<QuestionModel> findPwd(@QueryMap Map<String, String> map) {
        return genApi().findPwd(map);
    }

    @Override
    public Observable<AnswerModel> checkAnswer(@QueryMap Map<String, String> map) {
        return genApi().checkAnswer(map);
    }

    @Override
    public Observable<UpdateModel> resetPwd(@QueryMap Map<String, String> map) {
        return genApi().resetPwd(map);
    }

    @Override
    public Observable<LoginOutModel> logout(@QueryMap Map<String, String> map) {
        return genApi().logout(map);
    }

    @Override
    public Observable<LoginModel> updateInformation(@Part("json") RequestBody requestBody, @Part("session") RequestBody requestBody1) {
        return genApi().updateInformation(requestBody, requestBody1);
    }

    @Override
    public Observable<LoginOutModel> getUserInfo(@QueryMap Map<String, String> map) {
        return genApi().getUserInfo(map);
    }


}
