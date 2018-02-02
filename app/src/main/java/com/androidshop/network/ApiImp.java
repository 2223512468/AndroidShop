package com.androidshop.network;

import com.androidshop.model.AnswerModel;
import com.androidshop.model.LoginModel;
import com.androidshop.model.QuestionModel;
import com.androidshop.model.RegisterModel;
import com.androidshop.model.UpdateModel;

import java.util.Map;

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
    public Observable<RegisterModel> register(@QueryMap Map<String, String> map) {
        return genApi().register(map);
    }

    @Override
    public Observable<LoginModel> login(@QueryMap Map<String, String> map) {
        return genApi().login(map);
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


}
