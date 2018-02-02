 package com.androidshop.utils;

 import android.app.Activity;
 import android.content.Context;
 import android.os.IBinder;
 import android.view.MotionEvent;
 import android.view.View;
 import android.view.View.OnTouchListener;
 import android.view.ViewGroup;
 import android.view.WindowManager;
 import android.view.inputmethod.InputMethodManager;
 import android.widget.EditText;

 import java.util.Timer;
 import java.util.TimerTask;

 public class SoftPanUtil {
     /**
      * 隐藏软键盘
      *
      * @param c
      *            ：哪个activity中
      */
     public static void hideSoftpan(Activity c) {
         if (c.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) { // 关闭输入法
             InputMethodManager imm = (InputMethodManager) c
                     .getApplicationContext().getSystemService(
                             Context.INPUT_METHOD_SERVICE);
             imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
         }
     }

     /**
      * 设置控件点击事件监听，如果不是EditText就隐藏软键盘
      *
      * @param a
      *            ：哪个activity
      * @param view
      *            :父布局
      */
     public static void setOnTouchOutsideHideSoftPan(final Activity a, View view) {
         if (!(view instanceof EditText)) {
             view.setOnTouchListener(new OnTouchListener() {

                 @Override
                 public boolean onTouch(View v, MotionEvent event) {
                     hideSoftKeyboard(a); // Main.this是我的activity名
                     return false;
                 }
             });
             if (view instanceof ViewGroup) {
                 for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                     View innerView = ((ViewGroup) view).getChildAt(i);
                     setOnTouchOutsideHideSoftPan(a, innerView);
                 }
             }
         }
     }

     public static void hideSoftKeyboard(Activity activity) {
         InputMethodManager inputMethodManager = (InputMethodManager) activity
                 .getSystemService(Activity.INPUT_METHOD_SERVICE);
         View v = activity.getCurrentFocus();
         if (v != null) {
             IBinder ibinder = v.getWindowToken();
             if (ibinder != null && inputMethodManager != null) {
                 inputMethodManager.hideSoftInputFromWindow(ibinder, 0);
             }
         }
     }


     /**
      * 打卡软键盘
      *
      * @param mEditText 输入框
      * @param mContext  上下文
      */
     public static void openKeybord(final EditText mEditText, final Context mContext) {

         //必须要等UI绘制完成之后，打开软键盘的代码才能生效，所以要设置一个延时
         Timer timer = new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 InputMethodManager imm = (InputMethodManager) mContext
                         .getSystemService(Context.INPUT_METHOD_SERVICE);
                 imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
                 imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                         InputMethodManager.HIDE_IMPLICIT_ONLY);
             }
         }, 500);
     }

 }