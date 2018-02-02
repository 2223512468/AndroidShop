package com.androidshop.utils;

import android.content.Context;
import android.widget.Toast;


public class T {

    private T() {
        /** 防止被实例化 **/
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示
     *
     * @param ctx
     * @param msg
     */
    public static void showShort(Context ctx, CharSequence msg) {
        if (isShow) {
            if (ctx != null) {
                Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 短时间显示
     *
     * @param ctx
     * @param msg
     */
    public static void showShort(Context ctx, int msg) {
        if (isShow) {
            if (ctx != null) {
                Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void showShort(Context ctx, String msg) {
        if (isShow) {
            if (ctx != null) {
                Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 长时间显示
     *
     * @param ctx
     * @param msg
     */
    public static void showLong(Context ctx, CharSequence msg) {
        if (isShow) {
            if (ctx != null) {
                Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 长时间显示
     *
     * @param ctx
     * @param msg
     */
    public static void showLong(Context ctx, int msg) {
        if (isShow) {
            if (ctx != null) {
                Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 自定义时间显示
     *
     * @param ctx
     * @param msg
     */
    public static void show(Context ctx, CharSequence msg, int duration) {
        if (isShow) {
            if (ctx != null) {
                Toast.makeText(ctx, msg, duration).show();
            }
        }
    }

    /**
     * 自定义时间显示
     *
     * @param ctx
     * @param msg
     */
    public static void show(Context ctx, int msg, int duration) {
        if (isShow) {
            if (ctx != null) {
                Toast.makeText(ctx, msg, duration).show();
            }
        }
    }

}
