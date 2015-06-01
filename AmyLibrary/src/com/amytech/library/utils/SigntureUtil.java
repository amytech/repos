package com.amytech.library.utils;

import android.content.Context;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Iterator;
import java.util.List;

/**
 * 签名工具类
 * 
 * @author AmyTech
 */
public class SigntureUtil {

	public static String getSignture(Context context) {
		PackageManager pm = context.getPackageManager();
		List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
		Iterator<PackageInfo> iter = apps.iterator();
		while (iter.hasNext()) {
			PackageInfo packageinfo = iter.next();
			String packageName = packageinfo.packageName;
			if (packageName.equals(context.getPackageName())) {
				return packageinfo.signatures[0].toCharsString();
			}
		}
		return null;
	}
}
