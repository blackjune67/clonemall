package com.june.apiserver.service;

import com.june.apiserver.dto.TodoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class TodoServiceTest {

    @Autowired
    TodoService todoService;

    @Test
    @DisplayName("testGet")
    public void test01() {
        Long tno = 50L;
        TodoDto todoDto = todoService.get(tno);
        log.info("testGet ={}", todoDto);
    }

    @Test
    @DisplayName("testRegister")
    public void test02() {
        // given
        TodoDto todoDto = TodoDto.builder()
                .title("제목")
                .content("내용입니다")
                .dueDate(LocalDate.of(2023, 12, 31))
                .build();
        log.info("testResgister ={}", todoService.resgister(todoDto));
    }
}
