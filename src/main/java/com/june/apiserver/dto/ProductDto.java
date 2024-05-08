package com.june.apiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    // 상품 등록, 조회

    private Long pno;
    private String pname;
    private int price;
    private String pdesc;

    private boolean delFlag;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    // 조회할 때 사용 및 업로드한 파일 이름가져올 때
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();
}