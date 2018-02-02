package com.androidshop.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidshop.R;
import com.androidshop.event.EventMessage;
import com.androidshop.utils.SoftPanUtil;
import com.androidshop.widget.varyhelperview.VaryViewHelperController;
import com.androidshop.widget.view.LoginDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import rx.Subscription;


/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {
    private String mTag;
    protected ProgressDialog mDialog;
    public Context mContext;
    protected Subscription mSubscription;

    private VaryViewHelperController mVaryViewHelperController = null;

    protected TextView mToobar;

    protected ImageView mTitleBack;

    protected ImageView mTitleHome;

    protected abstract int getViewId();

    protected abstract void init();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View view;
        if (getViewId() != 0) {
            view = inflater.inflate(getViewId(), null);
            SoftPanUtil.setOnTouchOutsideHideSoftPan(getActivity(), view);
        } else {
            view = super.onCreateView(inflater, container, savedInstanceState);
        }
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    /**
     * 为mVaryViewHelperController赋值
     *
     * @param view :基于哪个view显示
     */
    public void initViewController(View view) {
        if (null != view) {
            mVaryViewHelperController = new VaryViewHelperController(view);
        }
    }

    public void setFragmentTag(@NonNull String tag) {
        mTag = tag;
    }

    public String getFragmentTag() {
        return mTag;
    }

    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void showLoading(boolean toggle, String msg) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showLoading(msg);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void showEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show error
     *
     * @param toggle
     */
    protected void showError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showError(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    /**
     * 显示动画弹窗
     *
     * @param text
     */
    protected void showDialog(@NonNull String text) {
        if (null == mDialog) {
            mDialog = new ProgressDialog(getActivity());
        }
        mDialog.setMessage(text);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 去动画弹窗
     */
    protected void dissmissDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 打开一个新的activity
     *
     * @param cla
     */
    protected void startActivity(Class<?> cla) {
        Intent intent = new Intent(getActivity(), cla);
        startActivity(intent);
    }

    /**
     * 去动画弹窗
     */
    protected void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 在主线程运行
     *
     * @param event
     */
    public void onEventMainThread(EventMessage event) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleSomethingElse(EventMessage event) {
        onEventMainThread(event);
    }


    /**
     * 弹出登录失效的对话框
     *
     * @param action 0:直接退出当前页
     */
    public void showLoginStatusErrorDialog(final int action) {
        LoginDialog.Builder builder = new LoginDialog.Builder(mContext);
        builder.setMessage(R.string.re_login);
        builder.setIsCanel(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //   LoginUtil.clearInfo(mContext);
                if (action == 0) {
                    getActivity().finish();
                }
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loginOut();
                if (action == 0) {
                    getActivity().finish();
                }
                //  startActivity(LoginAct.class);
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    /**
     * 退出登录
     */
    protected void loginOut() {
       /* SPUtils.clear(mContext);
        LoginUtil.clearInfo(mContext);
        SPUtils.put(mContext, "isLogin", false);
        EventBus.getDefault().post(new EventMessage(BaseActivity.LOGIN_OUT_ACTION, "退出"));*/
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
