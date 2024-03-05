package com.june.apiserver.repository.search;

import com.june.apiserver.domain.QTodo;
import com.june.apiserver.domain.Todo;
import com.june.apiserver.dto.PageRequestDto;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {
    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1(PageRequestDto pageRequestDto) {
        log.info("search=======================================!!");
        QTodo todo = QTodo.todo;
        JPQLQuery<Todo> query = from(todo);
//        JPQLQuery<Todo> where = query.where(todo.title.contains("1"));

        Pageable pageable = PageRequest.of(
                pageRequestDto.getPage() -1,
                pageRequestDto.getSize(),
                Sort.by("tno").descending()
        );

        this.getQuerydsl().applyPagination(pageable, query);

        List<Todo> list = query.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(list, pageable, total);
    }
}
