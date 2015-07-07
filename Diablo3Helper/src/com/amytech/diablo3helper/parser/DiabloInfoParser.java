package com.amytech.diablo3helper.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.amytech.android.library.base.BaseJsoupParser;
import com.amytech.diablo3helper.model.DiabloInfoModel;

public class DiabloInfoParser extends BaseJsoupParser {
	public static List<DiabloInfoModel> parseList(String html) {
		List<DiabloInfoModel> result = new ArrayList<DiabloInfoModel>();

		Document document = Jsoup.parse(html);
		if (document != null) {
			Elements itemListElement = document.getElementsByAttributeValue("class", "clearfix block");
			if (isNotEmpty(itemListElement)) {
				int count = itemListElement.size();
				for (int i = 0; i < count; i++) {
					itemListElement.get(i);
				}
			}
		}

		return result;
	}
}
