package com.amytech.diablo3helper.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amytech.android.library.base.BaseJsoupParser;
import com.amytech.diablo3helper.model.DiabloInfoModel;

public class DiabloInfoParser extends BaseJsoupParser {
	public static List<DiabloInfoModel> parseList(String html) {
		List<DiabloInfoModel> result = new ArrayList<DiabloInfoModel>();

		Document document = Jsoup.parse(html);
		Elements itemListElements = getElementsByClass(document, "clearfix block");

		if (isNotEmpty(itemListElements)) {

			int itemCount = itemListElements.size();

			for (int i = 0; i < itemCount; i++) {
				String title = "";
				String desc = "";
				String imageURL = "";
				String detailURL = "";

				Element itemElement = itemListElements.get(i);

				Element imgElement = getFirstElementByClass(itemElement, "img");
				Element txtElement = getFirstElementByClass(itemElement, "txt");

				Element itemImageElement = getFirstElementByTag(imgElement, "img");
				if (itemImageElement != null) {
					imageURL = itemImageElement.attr("data-original");
				}

				Element itemDetailElement = getFirstElementByTag(txtElement, "a");
				if (itemDetailElement != null) {
					title = itemDetailElement.text();
					detailURL = itemDetailElement.attr("href");
				}

				Element itemDescElement = getFirstElementByClass(txtElement, "t02");
				if (itemDescElement != null) {
					desc = itemDescElement.text();
				}

				result.add(new DiabloInfoModel(title, desc, imageURL, detailURL));
			}
		}

		return result;
	}
}
