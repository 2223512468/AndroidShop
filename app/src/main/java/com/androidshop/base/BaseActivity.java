package com.androidshop.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.androidshop.R;
import com.androidshop.event.EventMessage;
import com.androidshop.utils.StringUtil;
import com.androidshop.widget.varyhelperview.VaryViewHelperController;
import com.androidshop.widget.view.LoginDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import rx.Subscription;


/**
 * AppCompatActivity基类
 * Created by lhz
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 退出登录的动作
     */
    public static final int LOGIN_OUT_ACTION = 0X99;
    protected Context mContext;
    protected ProgressDialog mDialog;
    protected Subscription mSubscription;

    /**
     * 用于显示加载动画 和 内容为空 或请求出错的提示界面
     */
    private VaryViewHelperController mVaryViewHelperController = null;

    /**
     * 设置界面布局id
     *
     * @return :layout id
     */
    protected abstract int getViewId();

    /**
     * 业务处理
     */
    protected abstract void initEvent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置软键盘状态
        if (getViewId() != 0) {
            View view = LayoutInflater.from(this).inflate(getViewId(), null);
            setContentView(view);
        }
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mContext = this;
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        dissmisProgressDialog();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 显示动画弹窗
     *
     * @param text ：显示的问题
     */
    public void showProgressDialog(@NonNull String text) {
        if (null == mDialog) {
            mDialog = new ProgressDialog(this);
        }
        mDialog.setMessage(text);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 去动画弹窗
     */
    public void dissmisProgressDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 去动画弹窗
     */
    protected void showToast(String text) {
        if (!StringUtil.isEmpty(text)) {
            //去换行
            Toast.makeText(this, text.replaceAll("\n", ""), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 去动画弹窗
     */
    protected void showToast(int stringId) {
        if (stringId > 0) {
            //去换行
            Toast.makeText(this, getResources().getString(stringId), Toast.LENGTH_SHORT).show();
        }
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

    /**
     * 为mVaryViewHelperController赋值
     *
     * @param viewId:基于哪个viewId显示
     */
    public void initViewController(int viewId) {
        View view = this.findViewById(viewId);
        if (null != view) {
            mVaryViewHelperController = new VaryViewHelperController(view);
        }
    }

    /**
     * toggle show loading 注意：调用前 要通过initViewController 设置动画依附于哪个view
     *
     * @param toggle :是否显示进度动画
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
     * toggle show empty 注意：调用前 要通过initViewController 设置动画依附于哪个view
     *
     * @param toggle:是否去进度动画
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
     * toggle show error 注意：调用前 要通过initViewController 设置动画依附于哪个view
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

    /**
     * 打开新activity
     *
     * @param tClass
     */
    public void gotoNewAty(Class<?> tClass) {
        Intent intent = new Intent(this, tClass);
        startActivity(intent);
    }

    /**
     * 打开新activity,并且接受返回
     *
     * @param tClass
     */
    public void gotoNewAtyForResult(Class<?> tClass, int requestCode) {
        Intent intent = new Intent(this, tClass);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 在主线程运行
     *
     * @param event
     */
    public void onEventMainThread(EventMessage event) {
    }

    @Subscribe
    public void onEvent(EventMessage event) {
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
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (action == 0) {
                    finish();
                }
            }
        });
        builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (action == 0) {
                    finish();
                }
                //     gotoNewAty(LoginAct.class);
            }
        });
        LoginDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //loginOut();
            }
        });
        dialog.show();
    }

    public void showLoginStatusDiyErrorDialog(final int action) {
        LoginDialog.Builder builder = new LoginDialog.Builder(mContext);
        builder.setMessage(R.string.re_login);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                //    gotoNewAty(LoginAct.class);
            }
        });
        LoginDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // loginOut();
            }
        });
        dialog.show();
    }

}
