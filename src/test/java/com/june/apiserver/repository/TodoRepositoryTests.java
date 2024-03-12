package com.june.apiserver.repository;

import com.june.apiserver.domain.Todo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    @DisplayName("todoRepository는 null이 아니다")
    public void test01() {
        Assertions.assertNotNull(todoRepository);
        log.info("log ={}", todoRepository.getClass().getName());
    }

    @Test
    @DisplayName("insert")
    public void test02() {
        for (int i = 0; i < 50; i++) {
            Todo todo = Todo.builder()
                    .title("title" + i)
                    .content("내용" + i)
                    .dueDate(LocalDate.of(2024, 2, 23))
                    .build();
            todoRepository.save(todo);
        }
    }

    @Test
    @DisplayName("delete")
    public void delete() {
        // given
        todoRepository.deleteById(98L);
        log.info("delete log ={}", todoRepository.findById(98L));
        // when

        // then
    }

    @Test
    @DisplayName("select")
    public void test03() {
        // given
        Long tno = 32L;
        Optional<Todo> result = todoRepository.findById(tno);
        // when
        Todo todo = result.orElseThrow();
        // then
        log.info("select log ={}", todo);
    }

    @Test
    @DisplayName("update")
    public void test04() {
        // given
        Long tno = 1L;
        Optional<Todo> result = todoRepository.findById(tno);
        // when
        Todo todo = result.orElseThrow();
        todo.changeTitle("update title");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2023, 10, 10));

        todoRepository.save(todo);
        // then
        log.info("select log ={}", todo);
    }

    @Test
    @DisplayName("pageable")
    public void test05() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(paging);
        log.info("페이징 ={}", result.getTotalElements());
        log.info("페이징 getContent ={}", result.getContent());
    }

    /*@Test
    @DisplayName("testSearch01")
    public void testSearch01() {
        todoRepository.search1();
    }*/
}
