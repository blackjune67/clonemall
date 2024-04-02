package com.june.apiserver.controller;

import com.june.apiserver.dto.ProductDto;
import com.june.apiserver.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final CustomFileUtil fileUtil;

    @PostMapping("/")
    public Map<String, String> register(ProductDto productDto) {
        log.info("register ={}", productDto);

//        List<String> uploadFileNames = fileUtil.saveFiles(productDto.getFiles());

        List<MultipartFile> files = productDto.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);

        productDto.setUploadFileNames(uploadFileNames);
        log.info("uploadFileNames ={}", uploadFileNames);
        return Map.of("result", "success");
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName) {
        return fileUtil.getFile(fileName);
    }
}
