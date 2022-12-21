package com.mkrzyszczyk.shop.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mkrzyszczyk.shop.admin.product.service.ExistingFileRenameUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ExistingFileRenameUtilsTest {

  @Test
  void shouldNotRenameFile(@TempDir Path tempDir) {
    String newName = ExistingFileRenameUtils.renameIfExist(tempDir, "test.png");
    assertEquals("test.png", newName);
  }

  @Test
  @SneakyThrows
  void shouldRenameExistingFile(@TempDir Path tempDir) {
    Files.createFile((tempDir.resolve("test.png")));
    String newName = ExistingFileRenameUtils.renameIfExist(tempDir, "test.png");
    assertEquals("test-1.png", newName);
  }

  @Test
  @SneakyThrows
  void shouldRenameExistingFileMultipleTimes(@TempDir Path tempDir) {
    Files.createFile((tempDir.resolve("test.png")));
    Files.createFile((tempDir.resolve("test-1.png")));
    Files.createFile((tempDir.resolve("test-2.png")));
    Files.createFile((tempDir.resolve("test-3.png")));
    String newName = ExistingFileRenameUtils.renameIfExist(tempDir, "test.png");
    assertEquals("test-4.png", newName);
  }

}