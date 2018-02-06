package com.androidshop.feature.user;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.model.LoginOutModel;
import com.androidshop.network.ApiImp;
import com.androidshop.utils.LoginUtil;
import com.androidshop.utils.T;
import com.androidshop.widget.cache.DataCleanManager;
import com.androidshop.widget.view.CacheDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2018/2/3.
 */
public class SettingAct extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.rl_cache)
    RelativeLayout rlCache;
    @BindView(R.id.rl_ziliao)
    RelativeLayout ziliao;
    @BindView(R.id.rl_safe)
    RelativeLayout rlSafe;
    @BindView(R.id.btn_exit)
    Button exitBtn;

    private Map<String, String> param = new HashMap<>();
    private String totalCacheSize;//缓存大小


    @Override
    protected int getViewId() {
        return R.layout.act_setting;
    }

    @Override
    protected void initEvent() {
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setListener();
    }

    private void setListener() {
        rlCache.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
        ziliao.setOnClickListener(this);
        rlSafe.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rl_cache:
                clearCache();
                break;
            case R.id.btn_exit:
                loginOut();
                break;
            case R.id.rl_ziliao:
                gotoNewAty(DataAct.class);
                break;
            case R.id.rl_safe:
                gotoNewAty(AccountAct.class);
                break;
        }
    }


    private void clearCache() {
        CacheDialog.Builder builde = new CacheDialog.Builder(SettingAct.this);
        builde.setMessage("是否清除缓存")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        DataCleanManager.cleanInternalCache(SettingAct.this);
                        T.showShort(SettingAct.this, "已清除" + totalCacheSize + "缓存");
                        try {
                            totalCacheSize = DataCleanManager.getTotalCacheSize(SettingAct.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        builde.create().show();
    }

    private void loginOut() {
        showProgressDialog("正在退出...请稍候");
        ApiImp.get().logout(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginOutModel>() {
                    @Override
                    public void onCompleted() {
                        dissmisProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginOutModel model) {
                        if (model.getStatus() == 0) {
                            LoginUtil.clearInfo(SettingAct.this);
                            gotoNewAty(LoginAct.class);
                        }
                    }
                });

    }


}
