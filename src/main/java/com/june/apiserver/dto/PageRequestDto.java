package com.june.apiserver.dto;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@Builder
@SuperBuilder
public class PageRequestDto {
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;


}
