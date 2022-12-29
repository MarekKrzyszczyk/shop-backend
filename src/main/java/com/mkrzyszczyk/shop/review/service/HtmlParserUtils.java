package com.mkrzyszczyk.shop.review.service;

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
