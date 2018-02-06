package com.androidshop.feature.user;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.model.LoginModel;
import com.androidshop.utils.LoginUtil;

import butterknife.BindView;

/**
 * Created by ${Terry} on 2018/2/6.
 */
public class AccountAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.phone_tv)
    TextView userName;
    @BindView(R.id.rl_change_pwd)
    RelativeLayout rlChangePwd;
    @BindView(R.id.textView2)
    TextView textView2;
    private LoginModel loginModel;


    @Override
    protected int getViewId() {
        return R.layout.act_account;
    }

    @Override
    protected void initEvent() {
        textView2.setText(R.string.safe_user);
        setLisener();
    }

    private void setLisener() {
        rlChangePwd.setOnClickListener(this);
        loginModel = LoginUtil.getInfo(mContext);
        if (loginModel != null) {
            userName.setText(loginModel.getData().getUser().getUsername());
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_change_pwd:
                gotoNewAty(ChangePwdAct.class);
                break;
        }
    }
}
