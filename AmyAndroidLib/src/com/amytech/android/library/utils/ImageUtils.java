package com.amytech.android.library.utils;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

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

	public static void displayImage(Context context, ImageView imageView, String imageURL, int widthDimens, int heightDimens) {
		Picasso.with(context).load(Uri.decode(imageURL)).resizeDimen(widthDimens, heightDimens).centerCrop().into(imageView);
	}
}
