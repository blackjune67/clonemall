package com.june.apiserver.service;

import com.june.apiserver.domain.Todo;
import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.TodoDto;
import com.june.apiserver.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDto get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return entityToDto(todo);
    }

    @Override
    public Long resgister(TodoDto dto) {
        Todo todo = dtoToEntity(dto);
        Todo result = todoRepository.save(todo);
        return result.getTno();
    }

    @Override
    public PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto) {
        Page<Todo> result =
        return null;
    }

    @Override
    public void modify(Todo dto) {
        // * 데이터베이스에 있는 데이터 가져옴
        Optional<Todo> result = todoRepository.findById(dto.getTno());

        Todo todo = result.orElseThrow();
        // * 수정
        todo.changeTitle(dto.getTitle());
        todo.changeContent(dto.getContent());
        todo.changeComplete(dto.isComplete());
        todo.changeDueDate(dto.getDueDate());

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
