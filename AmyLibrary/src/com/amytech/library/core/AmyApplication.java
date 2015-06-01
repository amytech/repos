package com.amytech.library.core;

import android.app.Application;
import android.util.DisplayMetrics;

import com.amytech.library.utils.NLog;
import com.amytech.library.utils.SigntureUtil;

/**
 * Default Application
 * 
 * @author AmyTech
 */
public class AmyApplication extends Application {
	/**
	 * 屏幕宽度（像素）
	 */
	public static int SCREEN_WIDTH;

	/**
	 * 屏幕高度（像素）
	 */
	public static int SCREEN_HEIGHT;

	/**
	 * 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
	 */
	public static float DENSITY;

	/**
	 * 屏幕密度（每寸像素：120/160/240/320）
	 */
	public static int DENSITY_DPI;

	protected static Application instance;

	@Override
	public void onCreate() {
		super.onCreate();

		instance = this;

		// 初始化屏幕参数
		DisplayMetrics dm = getResources().getDisplayMetrics();
		SCREEN_WIDTH = dm.widthPixels;
		SCREEN_HEIGHT = dm.heightPixels;
		DENSITY = dm.density;
		DENSITY_DPI = dm.densityDpi;
		NLog.d(AmyApplication.class, "Screen Width : " + SCREEN_WIDTH + ", Screen Height : " + SCREEN_HEIGHT);
		NLog.d(AmyApplication.class, "Density : " + DENSITY + ", Density DPI : " + DENSITY_DPI);

		NLog.d("============== Signture Information===============");
		NLog.d(SigntureUtil.getSignture(instance));
		NLog.d("============== Signture Information===============");
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	public static Application getApplication() {
		return instance;
	}
}
