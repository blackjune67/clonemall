package com.june.apiserver.repository.search;

import com.june.apiserver.domain.QTodo;
import com.june.apiserver.domain.Todo;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {
    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1() {
        log.info("search=======================================!!");
        QTodo todo = QTodo.todo;
        JPQLQuery<Todo> query = from(todo);
        JPQLQuery<Todo> where = query.where(todo.title.contains("1"));
        Pageable paging = PageRequest.of(1, 10, Sort.by("tno").descending());
        this.getQuerydsl().applyPagination(paging, query);

        query.fetch();
        query.fetchCount();

        return null;
    }
}
