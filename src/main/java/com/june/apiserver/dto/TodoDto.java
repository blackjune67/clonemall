package com.june.apiserver.mall.dto;

import jakarta.persistence.Column;
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
