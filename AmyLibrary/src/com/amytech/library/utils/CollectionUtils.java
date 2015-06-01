package com.amytech.library.utils;

import java.util.Collection;

/**
 * Collection Utils
 * @author AmyTech
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
