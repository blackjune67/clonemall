package com.june.apiserver.mall.repository;

import com.june.apiserver.mall.domain.Todo;
import com.june.apiserver.mall.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

}
