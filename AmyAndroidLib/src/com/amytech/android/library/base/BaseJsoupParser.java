package com.amytech.android.library.base;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BaseJsoupParser {
	protected static boolean isNotEmpty(Elements elements) {
		return elements != null && elements.size() > 0;
	}

	protected static Elements getElementsByClass(Element e, String className) {
		if (e != null) {
			Elements result = e.getElementsByAttributeValue("class", className);
			if (isNotEmpty(result)) {
				return result;
			}
		}

		return null;
	}

	protected static Element getFirstElementByClass(Element e, String className) {
		Elements elements = getElementsByClass(e, className);
		if (elements != null && elements.size() > 0) {
			return elements.get(0);
		}

		return null;
	}

	protected static Elements getElementsByTag(Element e, String tagName) {
		if (e != null) {
			Elements result = e.getElementsByTag(tagName);
			if (isNotEmpty(result)) {
				return result;
			}
		}

		return null;
	}

	protected static Element getFirstElementByTag(Element e, String tagName) {
		Elements elements = getElementsByTag(e, tagName);
		if (elements != null && elements.size() > 0) {
			return elements.get(0);
		}

		return null;
	}
}
