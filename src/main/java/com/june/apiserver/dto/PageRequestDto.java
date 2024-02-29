package com.june.apiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
}
