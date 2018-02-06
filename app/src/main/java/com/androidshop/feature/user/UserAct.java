package com.androidshop.feature.user;

import android.view.View;
import android.widget.RelativeLayout;

import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.model.LoginOutModel;
import com.androidshop.network.ApiImp;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2018/2/3.
 */
public class UserAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_setting)
    RelativeLayout setting;
    @BindView(R.id.rl_shareApp)
    RelativeLayout shareApp;
    private Map<String, String> param = new HashMap<>();


    @Override
    protected int getViewId() {
        return R.layout.act_user;
    }

    @Override
    protected void initEvent() {


        setListener();
    }


    private void setListener() {
        setting.setOnClickListener(this);
        shareApp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_setting:
                gotoNewAty(SettingAct.class);
                break;
            case R.id.rl_shareApp:
                getUserInfo();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    private void getUserInfo() {
        ApiImp.get().getUserInfo(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginOutModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginOutModel loginOutModel) {

                    }
                });
    }

}
