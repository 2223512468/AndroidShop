package com.androidshop.feature.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.model.LoginModel;
import com.androidshop.network.ApiImp;
import com.androidshop.network.req.UserReq;
import com.androidshop.utils.LoginUtil;
import com.androidshop.utils.StringUtil;
import com.androidshop.utils.T;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2018/2/3.
 */
public class DataAct extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.ll_nickname)
    LinearLayout ll_nickname;
    @BindView(R.id.edit_nickname)
    EditText edit_nickname;
    @BindView(R.id.ll_sex)
    LinearLayout ll_sex;
    @BindView(R.id.tv_sex)
    EditText tv_sex;
    @BindView(R.id.ll_brithday)
    LinearLayout ll_brithday;
    @BindView(R.id.tv_birthday)
    EditText tv_birthday;
    @BindView(R.id.ll_answer)
    LinearLayout ll_answer;
    @BindView(R.id.tv_answer)
    EditText tv_answer;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.btn_send)
    Button btnSend;
    private UserReq req = new UserReq();
    private LoginModel loginModel;


    @Override
    protected int getViewId() {
        return R.layout.act_data;
    }

    @Override
    protected void initEvent() {

        setData();

    }

    private void setData() {
        loginModel = LoginUtil.getInfo(this);
        edit_nickname.setText(loginModel.getData().getUser().getUsername());
        tv_sex.setText(loginModel.getData().getUser().getEmail());
        tv_birthday.setText(loginModel.getData().getUser().getQuestion());
        tv_answer.setText(loginModel.getData().getUser().getAnswer());
        edit_phone.setText(loginModel.getData().getUser().getPhone());
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                isCheck();
                break;
        }
    }

    private void isCheck() {
        String username = edit_nickname.getText().toString();
        if (StringUtil.isEmpty(username)) {
            T.showShort(this, R.string.input_phone);
            return;
        }

        String email = tv_sex.getText().toString();
        if (StringUtil.isEmpty(email)) {
            T.showShort(this, R.string.input_mail);
            return;
        }
        String question = tv_birthday.getText().toString();
        if (StringUtil.isEmpty(question)) {
            T.showShort(this, R.string.input_question);
            return;
        }

        String answer = tv_answer.getText().toString();
        if (StringUtil.isEmpty(answer)) {
            T.showShort(this, R.string.input_answer);
            return;
        }

        String phone = edit_phone.getText().toString();
        if (StringUtil.isEmpty(phone)) {
            T.showShort(this, R.string.input_phone_num);
            return;
        }

        req.setId(loginModel.getData().getUser().getId());
        req.setUsername(username);
        req.setEmail(email);
        req.setPhone(phone);
        req.setAnswer(answer);
        req.setQuestion(question);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSONArray.toJSONString(req));
        LoginModel.DataEntity.SessionEntity session = loginModel.getData().getSession();
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/json"), JSONArray.toJSONString(session));
        ApiImp.get().updateInformation(requestBody, requestBody1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginModel model) {
                        if (model.getStatus() == 0) {
                            T.showShort(DataAct.this, "更新信息成功");
                            LoginUtil.clearInfo(mContext);
                            LoginUtil.saveInfo(mContext, model);
                        } else {
                            T.showShort(mContext, model.getMsg());
                            gotoNewAty(LoginAct.class);
                            finish();
                        }
                    }
                });
    }

}
