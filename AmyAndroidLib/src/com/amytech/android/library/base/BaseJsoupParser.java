package com.amytech.android.library.base;

import org.jsoup.select.Elements;

public class BaseJsoupParser {
	protected static boolean isNotEmpty(Elements elements) {
		return elements != null && elements.size() > 0;
	}
}
