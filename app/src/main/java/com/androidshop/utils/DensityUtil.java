package com.androidshop.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by fly on 15/12/24.
 * 单位转换类
 */
public class DensityUtil {
	/**
	 * dp和px的转换
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static float getDisplayWidthDp(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int width2 = outMetrics.widthPixels;
		return width2;
	}
	public static float getDisplayHeightDp(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int height2 = outMetrics.heightPixels;
		return height2;
	}
	public static float[] getDisplayWidthHeightDp(Context context, int imgW,int imgH) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int w = outMetrics.widthPixels;
		int h = outMetrics.heightPixels;

		float f[] = new float[2];
		f[0] = w;
		f[1] = imgW*w/imgH;
		return f;
	}


}
