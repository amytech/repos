package com.amytech.android.library.utils;

import android.content.Context;
import android.widget.ImageView;

import com.amytech.android.library.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月8日 下午3:25:27 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月8日 <br>
 *
 * @author marktlzhai
 */
public class ImageUtils {

	public static void init(Context context) {
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024);
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.defaultDisplayImageOptions(getDefaultDisplayOptions());
		config.writeDebugLogs();

		ImageLoader.getInstance().init(config.build());
	}

	public static DisplayImageOptions getDefaultDisplayOptions() {
		DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();

		builder = builder.showImageOnLoading(R.drawable.image_loader_icon);
		builder = builder.showImageForEmptyUri(R.drawable.image_loader_icon);
		builder = builder.showImageOnFail(R.drawable.image_loader_icon);
		builder = builder.cacheInMemory(true);
		builder = builder.cacheOnDisk(true);
		builder = builder.considerExifParams(true);
		builder = builder.imageScaleType(ImageScaleType.NONE_SAFE);
		builder = builder.displayer(new RoundedBitmapDisplayer(10));
		return builder.build();
	}

	public static DisplayImageOptions getDisplayOptions(int resource) {
		return getDisplayOptions(resource, resource, resource);
	}

	public static DisplayImageOptions getDisplayOptions(int loadingRes, int emptyRes, int errorRes) {
		DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();

		if (loadingRes > 0) {
			builder = builder.showImageOnLoading(loadingRes);
		}

		if (emptyRes > 0) {
			builder = builder.showImageForEmptyUri(emptyRes);
		}

		if (errorRes > 0) {
			builder = builder.showImageOnFail(errorRes);
		}

		builder = builder.cacheInMemory(true);
		builder = builder.cacheOnDisk(true);
		builder = builder.considerExifParams(true);
		builder = builder.imageScaleType(ImageScaleType.EXACTLY);
		builder = builder.displayer(new RoundedBitmapDisplayer(10));
		return builder.build();
	}

	public static void displayImage(ImageView imageView, String imageURL, DisplayImageOptions options) {
		ImageLoader.getInstance().displayImage(imageURL, imageView, options);
	}

	public static void displayImage(ImageView imageView, String imageURL, DisplayImageOptions options, ImageLoadingListener listener) {
		ImageLoader.getInstance().displayImage(imageURL, imageView, options, listener);
	}
}
