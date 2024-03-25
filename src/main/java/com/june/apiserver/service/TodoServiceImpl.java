package com.june.apiserver.service;

import com.june.apiserver.domain.Todo;
import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.TodoDto;
import com.june.apiserver.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDto get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return entityToDto(todo);
    }

    @Override
    public Long register(TodoDto dto) {
        Todo todo = dtoToEntity(dto);
        Todo result = todoRepository.save(todo);
        log.info("result = {}", result);
        return result.getTno();
    }

    @Override
    public PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto) {
        // JPA
        Page<Todo> result = todoRepository.search1(pageRequestDto);
        List<TodoDto> dtoList = result.get().map(this::entityToDto).toList();

        return PageResponseDto.<TodoDto>withAll()
                .dtoList(dtoList)
                .pageRequestDto(pageRequestDto)
                .total(result.getTotalElements())
                .build();
    }

    @Override
    public void modify(TodoDto todoDto) {
        // * 데이터베이스에 있는 데이터 가져옴
        Todo todo = todoRepository.findById(todoDto.getTno()).orElseThrow();

        // ! Optional을 사용하지 않고 코드를 간결하게 리팩토링함
        /*Optional<Todo> result = todoRepository.findById(todoDto.getTno());
        Todo todo = result.orElseThrow();*/
        // * 수정
        todo.changeTitle(todoDto.getTitle());
        todo.changeContent(todoDto.getContent());
        todo.changeComplete(todoDto.isComplete());
        todo.changeDueDate(todoDto.getDueDate());

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public TodoDto entityToDto(Todo todo) {
        return TodoService.super.entityToDto(todo);
    }

    @Override
    public Todo dtoToEntity(TodoDto todoDto) {
        return TodoService.super.dtoToEntity(todoDto);
    }
}
