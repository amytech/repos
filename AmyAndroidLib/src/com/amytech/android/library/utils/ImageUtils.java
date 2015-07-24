package com.amytech.android.library.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.amytech.android.library.R;
import com.squareup.picasso.Picasso;

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

	public static void displayImage(Context context, ImageView imageView, String imageURL, int widthDimens, int heightDimens, int noImageResource) {
		if (StringUtils.isNotEmpty(imageURL)) {
			Picasso.with(context).load(Uri.decode(imageURL)).resizeDimen(widthDimens, heightDimens).error(noImageResource).centerCrop().into(imageView);
		} else {
			imageView.setImageResource(noImageResource);
		}
	}

	public static void displayImage(Context context, ImageView imageView, String imageURL, int widthDimens, int heightDimens) {
		if (StringUtils.isNotEmpty(imageURL)) {
			Picasso.with(context).load(Uri.decode(imageURL)).resizeDimen(widthDimens, heightDimens).error(R.drawable.image_loader_icon).centerCrop()
					.into(imageView);
		} else {
			imageView.setImageResource(R.drawable.image_loader_icon);
		}
	}
}
