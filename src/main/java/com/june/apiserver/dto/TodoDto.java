package com.june.apiserver.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoDto {
    private long tno;
    private String title;
    private String content;
    private boolean complete;
    private LocalDate dueDate;
}
