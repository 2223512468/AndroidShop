package com.androidshop.feature.user;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSONArray;
import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.constant.Constant;
import com.androidshop.model.AnswerModel;
import com.androidshop.model.QuestionModel;
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
public class FindPwdAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ibtn_back)
    ImageButton ibtn_back;
    @BindView(R.id.edit_user)
    EditText edit_user;
    @BindView(R.id.edit_pwd_que)
    EditText edit_pwd_que;
    @BindView(R.id.edit_pwd_ans)
    EditText edit_pwd_ans;
    @BindView(R.id.find_pwd)
    Button find_pwd;

    private Map<String, String> queParam = new HashMap<>();
    private Map<String, String> checkParam = new HashMap<>();
    private String username;
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            queParam.put("username", username);
            findPwd();
        }
    };

    @Override
    protected int getViewId() {
        return R.layout.act_find_password;
    }

    @Override
    protected void initEvent() {

        setListener();
        edit_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (runnable != null) {
                    mHandler.removeCallbacks(runnable);
                }
                username = s.toString();
                mHandler.postDelayed(runnable, 800);
            }
        });
    }


    private void findPwd() {
        ApiImp.get().findPwd(queParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuestionModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(QuestionModel model) {
                        edit_pwd_que.setText(model.getData());
                    }
                });
    }

    private void isCheck() {
        String username = edit_user.getText().toString();
        if (StringUtil.isEmpty(username)) {
            T.showShort(this, "请输入用户名");
            return;
        }

        String question = edit_pwd_que.getText().toString();
        if (StringUtil.isEmpty(question)) {
            T.showShort(this, R.string.input_question);
            return;
        }

        String answer = edit_pwd_ans.getText().toString();
        if (StringUtil.isEmpty(answer)) {
            T.showShort(this, R.string.input_answer);
            return;
        }

        checkParam.put("username", username);
        checkParam.put("question", question);
        checkParam.put("answer", answer);
        checkAns();

    }

    private void checkAns() {
        ApiImp.get().checkAnswer(checkParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AnswerModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AnswerModel model) {
                        if (model.getStatus() == 0) {
                            T.showShort(FindPwdAct.this, "回答正确");
                            SharedUtils.putString(Constant.TOKEN, JSONArray.toJSONString(model));
                            gotoNewAty(ResetPwdAct.class);
                        } else {
                            T.showShort(FindPwdAct.this, "回答错误");
                        }
                        finish();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_pwd:
                isCheck();
                break;
        }
    }

    private void setListener() {
        find_pwd.setOnClickListener(this);
    }

}
