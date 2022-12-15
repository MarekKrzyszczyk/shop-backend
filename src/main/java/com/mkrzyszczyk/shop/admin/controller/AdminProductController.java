package com.mkrzyszczyk.shop.admin.controller;

import com.mkrzyszczyk.shop.admin.model.AdminProduct;
import com.mkrzyszczyk.shop.admin.model.dto.AdminProductDto;
import com.mkrzyszczyk.shop.admin.model.dto.UploadResponse;
import com.mkrzyszczyk.shop.admin.service.AdminProductImageService;
import com.mkrzyszczyk.shop.admin.service.AdminProductService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

  private final AdminProductService adminProductService;
  private final AdminProductImageService adminProductImageService;

  @GetMapping("/admin/products")
  public ResponseEntity<Page<AdminProduct>> getProducts(Pageable pageable) {
    return ResponseEntity.ok(adminProductService.getProducts(pageable));
  }

  @GetMapping("/admin/products/{id}")
  public ResponseEntity<AdminProduct> getProduct(@PathVariable Long id) {
    return ResponseEntity.ok(adminProductService.getProduct(id));
  }

  @PostMapping("/admin/products")
  public ResponseEntity<AdminProduct> createProduct(@RequestBody @Valid AdminProductDto adminProductDto) {
    return new ResponseEntity<>(adminProductService.createProduct(adminProductDto), HttpStatus.CREATED);
  }

  @PutMapping("/admin/products/{id}")
  public ResponseEntity<AdminProduct> updateProduct(@RequestBody AdminProductDto adminProductDto,
      @PathVariable Long id) {
    return ResponseEntity.ok(adminProductService.updateProduct(adminProductDto, id));
  }

  @DeleteMapping("/admin/products/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    adminProductService.deleteProduct(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/admin/products/upload-image")
  public UploadResponse uploadImage(@RequestParam("file") MultipartFile multipartFile) {
    String filename = multipartFile.getOriginalFilename();

    try (InputStream inputStream = multipartFile.getInputStream()) {
      return new UploadResponse(adminProductImageService.uploadImage(filename, inputStream));
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong while uploading file", e);
    }
  }

  @GetMapping("/data/productImage/{fileName}")
  public ResponseEntity<Resource> serveFiles(@PathVariable String fileName) throws IOException {
    Resource resource = adminProductImageService.serveFiles(fileName);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(fileName)))
        .body(resource);
  }
}