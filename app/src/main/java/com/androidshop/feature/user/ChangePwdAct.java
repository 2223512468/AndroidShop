package com.androidshop.feature.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidshop.R;
import com.androidshop.base.BaseActivity;
import com.androidshop.utils.StringUtil;
import com.androidshop.utils.T;

import butterknife.BindView;

/**
 * Created by ${Terry} on 2018/2/6.
 */
public class ChangePwdAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.edit_old_pwd)
    EditText editOpwd;
    @BindView(R.id.edit_new_pwd)
    EditText editNpwd;
    @BindView(R.id.edit_again_pwd)
    EditText editApwd;
    @BindView(R.id.btn_send)
    Button sendBtn;


    @Override
    protected int getViewId() {
        return R.layout.act_change_pwd;
    }

    @Override
    protected void initEvent() {
        textView2.setText(R.string.change_pwd);
        setListener();
    }

    private void setListener() {
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                break;
        }
    }

    private void isCheck() {

        String oldPwd = editOpwd.getText().toString();
        if (StringUtil.isEmpty(oldPwd)) {
            T.showShort(mContext, "请输入原密码");
            return;
        }

        String newPwd = editNpwd.getText().toString();
        if (StringUtil.isEmpty(newPwd)) {
            T.showShort(mContext, "请输入新密码");
            return;
        }

        String aPwd = editApwd.getText().toString();
        if (StringUtil.isEmpty(aPwd)) {
            T.showShort(mContext, "请确认密码");
            return;
        }

        if (!newPwd.equals(aPwd)) {
            T.showShort(mContext, "两次密码不一致，请重新输入");
            return;
        }
    }

}
