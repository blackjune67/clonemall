package com.june.apiserver.service;

import com.june.apiserver.domain.Todo;
import com.june.apiserver.dto.TodoDto;
import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {
    TodoDto get(Long tno);

    Long resgister(TodoDto dto);

    void modify(Todo dto);

    void remove(Long tno);


    default TodoDto entityToDto(Todo todo) {
        return TodoDto.builder()
                        .title(todo.getTitle())
                        .content(todo.getContent())
                        .complete(todo.isComplete())
                        .dueDate(todo.getDueDate())
                        .build();
    }

    default Todo dtoToEntity(TodoDto todoDto) {
        return Todo.builder()
                .title(todoDto.getTitle())
                .content(todoDto.getContent())
                .complete(todoDto.isComplete())
                .dueDate(todoDto.getDueDate())
                .build();
    }
}