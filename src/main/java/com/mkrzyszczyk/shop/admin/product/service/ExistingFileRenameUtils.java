package com.mkrzyszczyk.shop.admin.product.service;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ExistingFileRenameUtils {

  public static String renameIfExist(Path uploadDir, String filename) {
    if (Files.exists(uploadDir.resolve(filename))) {
      return renameAndCheck(uploadDir, filename);
    }
    return filename;
  }

  private static String renameAndCheck(Path uploadDir, String filename) {
    String newName = renameFilename(filename);
    if (Files.exists(uploadDir.resolve(newName))) {
      newName = renameAndCheck(uploadDir, newName);
    }
    return newName;
  }

  private static String renameFilename(String filename) {
    String baseName = FilenameUtils.getBaseName(filename);
    String[] split = baseName.split("-(?=[0-9]+$)");

    int suffix = split.length > 1 ? Integer.parseInt(split[split.length - 1]) + 1 : 1;

    return split[0] + "-" + suffix + "." + FilenameUtils.getExtension(filename);
  }

}
