package com.june.apiserver.repository;

import com.june.apiserver.domain.Todo;
import com.june.apiserver.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

}
