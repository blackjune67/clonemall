package com.june.apiserver.repository.search;

import com.june.apiserver.domain.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {
    Page<Todo> search1();

}
