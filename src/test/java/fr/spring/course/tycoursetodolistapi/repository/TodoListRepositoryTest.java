package fr.spring.course.tycoursetodolistapi.repository;

import fr.spring.course.tycoursetodolistapi.entity.PriorityType;
import fr.spring.course.tycoursetodolistapi.entity.Task;
import fr.spring.course.tycoursetodolistapi.entity.TodoList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TodoListRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoListRepository todoListRepository;

    @Test
    void whenSave_thenReturnTodoList() {
        // Given
        TodoList expectedTodoList = new TodoList(1, "Course", null);
        TodoList todoList = new TodoList("Course");

        // When
        TodoList todoListSaved = todoListRepository.save(todoList);

        // Then
        Assertions.assertThat(todoListSaved.getId()).isNotNull();
        Assertions.assertThat(todoListSaved).isEqualToIgnoringGivenFields(expectedTodoList, "id");
        entityManager.remove(todoListSaved);
    }
}