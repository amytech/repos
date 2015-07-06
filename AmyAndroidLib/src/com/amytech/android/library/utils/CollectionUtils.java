package com.amytech.android.library.utils;

import java.util.Collection;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月6日 上午11:44:15 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月6日 <br>
 *
 * @author marktlzhai
 */
public class CollectionUtils {
	public static <E> boolean isEmpty(Collection<E> c) {
		return c == null || c.size() == 0;

	}

	public static <E> boolean notEmpty(Collection<E> c) {
		return c != null && c.size() != 0;

	}

	public static <E> int size(Collection<E> c) {
		return c == null ? 0 : c.size();
	}
}
