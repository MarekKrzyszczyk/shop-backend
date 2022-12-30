package com.mkrzyszczyk.shop.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HtmlParserUtils {

  public static String clearHtmlContent(String html) {
    return Jsoup.clean(html, Safelist.none());
  }
}
