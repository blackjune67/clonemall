package com.june.apiserver.service;

import com.june.apiserver.domain.Todo;
import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.TodoDto;
import jakarta.transaction.Transactional;

//@Transactional
public interface TodoService {
    TodoDto get(Long tno);

    Long register(TodoDto dto);

    void modify(TodoDto dto);

    void remove(Long tno);

    PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto);

    default TodoDto entityToDto(Todo todo) {
        return TodoDto.builder()
                        .tno(todo.getTno())
                        .title(todo.getTitle())
                        .content(todo.getContent())
                        .complete(todo.isComplete())
                        .dueDate(todo.getDueDate())
                        .build();
    }

    default Todo dtoToEntity(TodoDto todoDto) {
        return Todo.builder()
                .tno(todoDto.getTno())
                .title(todoDto.getTitle())
                .content(todoDto.getContent())
                .complete(todoDto.isComplete())
                .dueDate(todoDto.getDueDate())
                .build();
    }
}