package com.june.apiserver.controller;

import com.june.apiserver.dto.ProductDto;
import com.june.apiserver.service.ProductService;
import com.june.apiserver.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final CustomFileUtil fileUtil;
    private final ProductService productService;

    /*@PostMapping("/")
    public Map<String, String> register(ProductDto productDto) {
        log.info("register ={}", productDto);

//        List<String> uploadFileNames = fileUtil.saveFiles(productDto.getFiles());

        List<MultipartFile> files = productDto.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);

        productDto.setUploadFileNames(uploadFileNames);
        log.info("uploadFileNames ={}", uploadFileNames);
        return Map.of("result", "success");
    }*/

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName) {
        return fileUtil.getFile(fileName);
    }

    @PostMapping("/")
    public Map<String, Long> register(ProductDto productDto) {
        List<MultipartFile> files = productDto.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);
        productDto.setUploadFileNames(uploadFileNames);

        log.info("==> uploadFileNames ={}", uploadFileNames);

        Long pno = productService.register(productDto);
        return Map.of("result", pno);
    }

    @GetMapping("/{pno}")
    public ProductDto read(@PathVariable("pno") Long pno) {
        return productService.get(pno);
    }

    @PutMapping("/{pno}")
    public Map<String, String> modify(@PathVariable("pno") Long pno, ProductDto productDto) {
        productDto.setPno(pno);
        ProductDto oldProductDTO = productService.get(pno);

        List<MultipartFile> files = productDto.getFiles();
        List<String> currentUploadFileNames = fileUtil.saveFiles(files);

        List<String> uploadFileNames = productDto.getUploadFileNames();

        if (currentUploadFileNames != null && !currentUploadFileNames.isEmpty()) {
            uploadFileNames.addAll(currentUploadFileNames);
        }

        productService.modify(productDto);
        List<String> oldFileNames = oldProductDTO.getUploadFileNames();
        if (oldFileNames != null && !oldFileNames.isEmpty()) {
            List<String> removeFiles =
                    oldFileNames.stream().filter(
                            fileName -> !uploadFileNames.contains(fileName)
                    ).collect(Collectors.toList());

            fileUtil.deleteFiles(removeFiles);
        }
        return Map.of("RESULT", "SUCCESS");
    }
}
