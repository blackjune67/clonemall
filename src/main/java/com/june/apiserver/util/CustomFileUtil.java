package com.june.apiserver.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${com.june.upload.path}")
    private String uploadPath;

    // 만약에 폴더가 없으면 생성 (첫 실행시)
    @PostConstruct
    public void init() {
        File tempFolder = new File(uploadPath);
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        uploadPath = tempFolder.getAbsolutePath();
        log.info("=============================");
        log.info("uploadPath={}", uploadPath);
    }

    // * 파일 업로드
    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException {
        if(files == null || files.isEmpty()) {
            return List.of();
        }

        List<String> uploadNames = new ArrayList<>();

        for (MultipartFile file : files)  {
            String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            Path savePath = Paths.get(uploadPath, savedName);

            try {
                Files.copy(file.getInputStream(), savePath);
                String contentType = file.getContentType();

                //이미지 파일이면 썸네일 생성
                if(contentType != null || contentType.startsWith("image")) {
                    Path thumnailpath = Paths.get(uploadPath, "s_" + savedName);
                    Thumbnails.of(savePath.toFile()).size(200, 200).toFile(thumnailpath.toFile());
                }

                uploadNames.add(savedName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return uploadNames;
    }

    public ResponseEntity<Resource> getFile(String fileName) throws RuntimeException {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        if(!resource.isReadable()) {
            // * 이미지가 없으면 아래 기본 이미지로 대체한다.
            resource = new FileSystemResource(uploadPath + File.separator+"default.jpeg");
        }

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    public void deleteFiles(List<String> fileNames) {
        if (fileNames == null || fileNames.isEmpty()) {
            return;
        }

        // * 썸네일 삭제
        fileNames.forEach(fileName -> {
            String thumbnailFileName = "s_" + fileName;

            Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
            Path filePath = Paths.get(uploadPath, fileName);

            try {
                Files.deleteIfExists(thumbnailPath);
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
