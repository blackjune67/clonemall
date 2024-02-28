package com.june.apiserver.service;

import com.june.apiserver.dto.TodoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TodoServiceTest {

    @Autowired
    TodoService todoService;

    @Test
    @DisplayName("testGet")
    public void test01() {
        // given
        Long tno = 50L;
        TodoDto todoDto = todoService.get(tno);
        log.info(String.valueOf(todoDto));
        // when

        // then
    }
}
