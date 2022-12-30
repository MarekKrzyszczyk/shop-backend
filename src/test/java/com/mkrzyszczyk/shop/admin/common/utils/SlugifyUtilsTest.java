package com.mkrzyszczyk.shop.admin.common.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mkrzyszczyk.shop.admin.common.utils.SlugifyUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SlugifyUtilsTest {

  @ParameterizedTest
  @CsvSource({
   "test test.png, test-test.png",
   "ąśćłó.png, asclo.png",
   "Product 1.png, product-1.png",
   "Product - 1.png, product-1.png",
   "Product  1.png, product-1.png",
   "Product__.png, product.png"
  })
  void shouldSlugifyFilename(String in, String out) {
    String actual = SlugifyUtils.slugifyFileName(in);
    assertEquals(out, actual);
  }

}