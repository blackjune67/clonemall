package com.june.apiserver.controller;

import com.june.apiserver.domain.Todo;
import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.TodoDto;
import com.june.apiserver.service.TodoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/todo")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ApiController {
    private final TodoService todoService;
    @GetMapping("/hi")
    public String hi() {
        log.info("hi!!");
        return "hi!!";
    }

    @GetMapping("/{tno}")
    public TodoDto get(@PathVariable("tno") Long tno) {
        log.info("tno = {}", tno);
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDto<TodoDto> list(PageRequestDto pageRequestDto) {
        log.info("pageRequestDto = {}", pageRequestDto);
        return todoService.getList(pageRequestDto);
    }
}
