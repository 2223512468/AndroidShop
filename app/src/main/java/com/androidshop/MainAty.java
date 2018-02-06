package com.androidshop;

import android.view.View;

import com.androidshop.base.BaseActivity;
import com.androidshop.feature.user.LoginAct;
import com.androidshop.feature.user.UserAct;
import com.androidshop.utils.LoginUtil;
import com.androidshop.widget.view.DragBubbleView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainAty extends BaseActivity implements View.OnClickListener, DragBubbleView.OnBubbleStateListener {

    @BindView(R.id.msg)
    DragBubbleView cartNum;
    @BindView(R.id.image_person)
    CircleImageView imagePerson;


    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvent() {

        cartNum.setText("99+");
        setListener();

    }

    @Override
    public void onDrag() {

    }

    @Override
    public void onMove() {

    }

    @Override
    public void onRestore() {

    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_person:
                if (LoginUtil.isLogin(this)) {
                    gotoNewAty(UserAct.class);
                    return;
                }
                gotoNewAty(LoginAct.class);
                break;
        }
    }

    private void setListener() {
        imagePerson.setOnClickListener(this);
        cartNum.setOnBubbleStateListener(this);
    }

}
