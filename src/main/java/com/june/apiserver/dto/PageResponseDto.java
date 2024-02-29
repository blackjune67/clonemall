package com.june.apiserver.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDto<E> {
    // * 진짜 데이터, 목록 데이터
    private List<E> dtoList;
    private List<Integer> pageNumberList;
    private PageRequestDto pageRequestDto;
    private boolean prev, next;
    private int totalCount, prevPage, nextPage, totalPage, current;

    public PageResponseDto(List<E> dtoList, PageRequestDto pageRequestDto, long total) {
        this.dtoList = dtoList;
        this.pageRequestDto = pageRequestDto;
        this.totalCount = (int) total;

        // * 끝페이지 end
        int end = (int) (Math.ceil(pageRequestDto.getPage() / 10.0)) * 10;

        // * 시작 번호
        int start = end - 9;

        // * 진짜 마지막 값
        int last = (int) Math.ceil(totalCount / (double) pageRequestDto.getSize());

        // * 끝 번호
        end = end > last ? last : end;

        this.prev = start > 1;
        this.next = totalCount > end * pageRequestDto.getSize();

        // * 페이지 번호 리스트
        this.pageNumberList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start - 1 : 0;
        this.nextPage = next ? end + 1 : 0;
    }
}
