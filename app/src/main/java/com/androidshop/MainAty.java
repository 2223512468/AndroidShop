package com.androidshop;

import android.support.v4.app.Fragment;
import android.view.View;

import com.androidshop.base.BaseActivity;
import com.androidshop.feature.frag.HomeFrag;
import com.androidshop.feature.frag.ItemFrag;
import com.androidshop.feature.frag.ShowFrag;
import com.androidshop.feature.user.LoginAct;
import com.androidshop.feature.user.UserAct;
import com.androidshop.model.FilterModel;
import com.androidshop.network.ApiImp;
import com.androidshop.utils.FilterSpUtil;
import com.androidshop.utils.LoginUtil;
import com.androidshop.widget.commontablayout.CommonTabLayout;
import com.androidshop.widget.commontablayout.CustomTabEntity;
import com.androidshop.widget.commontablayout.OnTabSelectListener;
import com.androidshop.widget.commontablayout.TabEntity;
import com.androidshop.widget.view.DragBubbleView;

import java.util.ArrayList;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainAty extends BaseActivity implements View.OnClickListener, DragBubbleView.OnBubbleStateListener {

    @BindView(R.id.msg)
    DragBubbleView cartNum;
    @BindView(R.id.image_person)
    CircleImageView imagePerson;
    @BindView(R.id.tablayout_bottom)
    CommonTabLayout mTabLayout;


    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvent() {

        cartNum.setText("99+");
        setListener();
        initFragments();

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
        getConfig();
    }

    private void initFragments() {
        mTabLayout.setTabData(genTabEntity(), getSupportFragmentManager(), R.id.container, genFragmnet());
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    private ArrayList<CustomTabEntity> genTabEntity() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        int[] mIconUnselectIds = {
                R.mipmap.ic_cat_01, R.mipmap.ic_cat_02,
                R.mipmap.ic_cat_03};
        int[] mIconSelectIds = {
                R.mipmap.ic_cat_01, R.mipmap.ic_cat_02,
                R.mipmap.ic_cat_03};
        for (int i = 0; i < mIconUnselectIds.length; i++) {
            mTabEntities.add(new TabEntity("", mIconSelectIds[i], mIconUnselectIds[i]));
        }
        return mTabEntities;
    }


    /**
     * 获取需要关联的fragment的数据
     *
     * @return ：关联的fragment的数据
     */

    private ArrayList<Fragment> genFragmnet() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeFrag());
        list.add(new ItemFrag());
        list.add(new ShowFrag());
        return list;
    }


    private void getConfig() {
        ApiImp.get().filter()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FilterModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FilterModel model) {
                        FilterSpUtil.saveModel(mContext, model);
                    }
                });
    }

}
