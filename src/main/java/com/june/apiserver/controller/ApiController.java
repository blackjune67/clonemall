package com.june.apiserver.controller;

import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.TodoDto;
import com.june.apiserver.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

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
    public TodoDto get(@PathVariable(name = "tno") Long tno) {
        log.info("tno = {}", tno);
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDto<TodoDto> list(PageRequestDto pageRequestDto) {
        log.info("pageRequestDto = {}", pageRequestDto);
        return todoService.getList(pageRequestDto);
    }

    @PostMapping("/")
    public Map<String, Object> register(@RequestBody TodoDto todoDto) {
        log.info("todoDto = {}", todoDto);
        // * 새로 만들어진 tno
        Long tno = todoService.register(todoDto);
        /*Map<String, Object> map = new HashMap<>();
        map.put("TNO", tno);
        return map;*/
        return Map.of("tno", tno);
    }

    // * 업데이트1
    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable("tno") Long tno, @RequestBody TodoDto todoDto) {
        log.info("tno = {}", tno);
        log.info("todoDto = {}", todoDto);
        todoService.modify(todoDto);
        return Map.of("result", "success");
    }

    // * 업데이트2
    @PutMapping("/{tno}/complete")
    public ResponseEntity<?> modify2(@PathVariable("tno") Long tno, @RequestBody TodoDto todoDto) {
        log.info("tno = {}", tno);
        log.info("todoDto = {}", todoDto);
        todoService.modify(todoDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "success"));
    }

    @DeleteMapping("/{tno}")
    public void remove(@PathVariable("tno") Long tno) {
        log.info("tno = {}", tno);
        todoService.remove(tno);
    }
}
