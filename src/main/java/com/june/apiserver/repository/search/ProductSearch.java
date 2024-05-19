package com.june.apiserver.repository.search;

import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.ProductDto;

public interface ProductSearch {
    PageResponseDto<ProductDto> searchList(PageRequestDto pageRequestDto);
}
