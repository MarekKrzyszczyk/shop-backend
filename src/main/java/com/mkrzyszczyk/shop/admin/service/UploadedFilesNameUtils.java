package com.mkrzyszczyk.shop.admin.service;

import com.github.slugify.Slugify;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UploadedFilesNameUtils {

  public static String slugifyFileName(String filename) {
    String baseName = FilenameUtils.getBaseName(filename);
    Slugify slg = new Slugify();
    String changedName = slg
        .withCustomReplacement("_", "-")
        .slugify(baseName);
    return changedName + "." + FilenameUtils.getExtension(filename);
  }
}
