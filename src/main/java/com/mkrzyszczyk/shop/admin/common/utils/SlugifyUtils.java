package com.mkrzyszczyk.shop.admin.common.utils;

import com.github.slugify.Slugify;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SlugifyUtils {

  public static String slugifyFileName(String filename) {
    String baseName = FilenameUtils.getBaseName(filename);
    Slugify slg = new Slugify();
    String changedName = slg
        .withCustomReplacement("_", "-")
        .slugify(baseName);
    return changedName + "." + FilenameUtils.getExtension(filename);
  }


  public static String slugify(String slug) {
    Slugify slugify = new Slugify();
    return slugify.withCustomReplacement("_", "-")
        .slugify(slug);
  }
}
