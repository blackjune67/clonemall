package com.june.apiserver.service;

import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductService {

    PageResponseDto<ProductDto> getList(PageRequestDto pageRequestDto);

    Long register(ProductDto productDto);

    ProductDto get(Long pno);

    void modify(ProductDto productDto);

    void remove(Long pno);

}
