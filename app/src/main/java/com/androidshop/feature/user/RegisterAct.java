package com.androidshop.feature.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSONArray;
import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.model.RegisterModel;
import com.androidshop.network.ApiImp;
import com.androidshop.network.req.UserReq;
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
 * 注册
 * Created by laotang on 2016/7/18.
 */
public class RegisterAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ibtn_back)
    ImageButton ibtn_back;
    @BindView(R.id.edit_user)
    EditText edit_user;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    @BindView(R.id.edit_pwd_again)
    EditText edit_pwd_again;
    @BindView(R.id.edit_mail)
    EditText edit_mail;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_find_question)
    EditText edit_find_question;
    @BindView(R.id.edit_find_answer)
    EditText edit_find_answer;
    @BindView(R.id.tv_register)
    Button tv_register;
    private Map<String, String> paramValue = new HashMap<>();
    private UserReq req = new UserReq();

    @Override
    protected int getViewId() {
        return R.layout.act_register;
    }

    @Override
    protected void initEvent() {

        setLisener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_back:
                /**
                 * 关闭当前页面
                 */
                finish();
                break;
            case R.id.tv_register:
                /**
                 * 注册
                 */
                isCheck();
                break;

            case R.id.tv_xieyi:
                /**
                 * 跳转到认证许可h5页面
                 */
                break;
        }
    }

    private void setLisener() {
        tv_register.setOnClickListener(this);
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

        String passwordAgain = edit_pwd_again.getText().toString();
        if (StringUtil.isEmpty(passwordAgain)) {
            T.showShort(this, R.string.input_pwd_again);
            return;
        }

        if (!password.equals(passwordAgain)) {
            T.showShort(this, "两次输入的密码不一致，请重新输入");
            return;
        }

        String email = edit_mail.getText().toString();
        if (StringUtil.isEmpty(email)) {
            T.showShort(this, R.string.input_mail);
            return;
        }

        if (!StringUtil.isEmail(email)) {
            T.showShort(this, "邮箱格式不正确，请重新输入");
            return;
        }

        String phone = edit_phone.getText().toString();
        if (StringUtil.isEmpty(phone)) {
            T.showShort(this, R.string.input_phone_num);
            return;
        }

        if (!StringUtil.isTel(phone)) {
            T.showShort(this, "手机格式不正确，请重新输入");
            return;
        }

        String question = edit_find_question.getText().toString();
        if (StringUtil.isEmpty(question)) {
            T.showShort(this, R.string.input_question);
            return;
        }

        String answer = edit_find_answer.getText().toString();
        if (StringUtil.isEmpty(answer)) {
            T.showShort(this, R.string.input_answer);
            return;
        }

        paramValue.put("username", username);
        paramValue.put("password", password);
        paramValue.put("email", email);
        paramValue.put("phone", phone);
        paramValue.put("question", question);
        paramValue.put("answer", answer);

        req.setUsername(username);
        req.setPassword(password);
        req.setEmail(email);
        req.setPhone(phone);
        req.setQuestion(question);
        req.setAnswer(answer);

        register();

    }

    private void register() {
        showProgressDialog("正在注册...请稍候");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSONArray.toJSONString(req));
        ApiImp.get().register(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterModel>() {
                    @Override
                    public void onCompleted() {
                        dissmisProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegisterModel model) {
                        T.showShort(RegisterAct.this, model.getMsg());
                        finish();
                    }
                });
    }

}
