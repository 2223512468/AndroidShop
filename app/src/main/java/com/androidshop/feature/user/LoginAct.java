package com.androidshop.feature.user;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.model.LoginModel;
import com.androidshop.network.ApiImp;
import com.androidshop.network.req.UserReq;
import com.androidshop.utils.LoginUtil;
import com.androidshop.utils.StringUtil;
import com.androidshop.utils.T;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2018/2/1.
 */
public class LoginAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.edit_user)
    EditText edit_user;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.tv_forget_pwd)
    TextView tv_forget_pwd;
    private Map<String, String> param = new HashMap<>();
    private UserReq req = new UserReq();


    @Override
    protected int getViewId() {
        return R.layout.act_login;
    }

    @Override
    protected void initEvent() {

        setListener();
    }


    private void setListener() {
        tv_register.setOnClickListener(this);
        tv_forget_pwd.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                gotoNewAty(RegisterAct.class);
                break;
            case R.id.tv_forget_pwd:
                gotoNewAty(FindPwdAct.class);
                break;
            case R.id.btn_login:
                isCheck();
                break;
        }
    }

    private void isCheck() {
        String username = edit_user.getText().toString();
        if (StringUtil.isEmpty(username)) {
            T.showShort(this, R.string.input_phone);
            return;
        }

        String password = edit_pwd.getText().toString();
        if (StringUtil.isEmpty(password)) {
            T.showShort(this, R.string.input_pwd);
            return;
        }
        param.put("username", username);
        param.put("password", password);

        req.setUsername(username);
        req.setPassword(password);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSONArray.toJSONString(req));

        showProgressDialog("正在登录...");
        ApiImp.get().login(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginModel>() {
                    @Override
                    public void onCompleted() {
                        dissmisProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginModel model) {
                        T.showShort(LoginAct.this, model.getMsg());
                        if (model.getStatus() == 0) {
                            LoginUtil.saveInfo(LoginAct.this, model);
                            Log.i("print", JSONArray.toJSONString(model));
                            finish();
                        }
                    }
                });

    }

}
