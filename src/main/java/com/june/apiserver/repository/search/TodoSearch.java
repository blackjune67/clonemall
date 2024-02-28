package com.june.apiserver.mall.repository.search;

import com.june.apiserver.mall.domain.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {
    Page<Todo> search1();

}
