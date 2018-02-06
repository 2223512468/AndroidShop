package com.androidshop.feature.user;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.constant.Constant;
import com.androidshop.model.AnswerModel;
import com.androidshop.model.UpdateModel;
import com.androidshop.network.ApiImp;
import com.androidshop.utils.SharedUtils;
import com.androidshop.utils.StringUtil;
import com.androidshop.utils.T;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2018/2/2.
 */
public class ResetPwdAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_pwd)
    Button updatePwd;
    @BindView(R.id.edit_user)
    EditText edit_user;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    private Map<String, String> param = new HashMap<>();


    @Override
    protected int getViewId() {
        return R.layout.act_reset_pwd;
    }

    @Override
    protected void initEvent() {
        setListener();
    }


    private void setListener() {
        updatePwd.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pwd:
                isCheck();
                break;
        }
    }

    private void isCheck() {
        String username = edit_user.getText().toString();
        if (StringUtil.isEmpty(username)) {
            T.showShort(ResetPwdAct.this, R.string.input_phone);
            return;
        }
        String newPassword = edit_pwd.getText().toString();
        if (StringUtil.isEmpty(newPassword)) {
            T.showShort(ResetPwdAct.this, R.string.input_new_pwd);
            return;
        }

        String info = SharedUtils.getString(Constant.TOKEN);
        AnswerModel model = JSON.parseObject(info, AnswerModel.class);
        if (model.getData() != null) {
            Log.i("print", model.getData());
        }

        param.put("username", username);
        param.put("passwordNew", newPassword);
        param.put("forgotToken", model.getData());
        resetPwd();

    }


    private void resetPwd() {
        ApiImp.get().resetPwd(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateModel model) {
                        if (model.getStatus() == 0) {
                            T.showShort(ResetPwdAct.this, model.getMsg());
                            finish();
                        } else {
                            T.showShort(ResetPwdAct.this, model.getMsg());
                        }
                    }
                });
    }

}
