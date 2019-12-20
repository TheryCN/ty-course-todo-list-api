package fr.spring.course.tycoursetodolistapi.repository;

import fr.spring.course.tycoursetodolistapi.entity.PriorityType;
import fr.spring.course.tycoursetodolistapi.entity.Task;
import fr.spring.course.tycoursetodolistapi.entity.TodoList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskRepository taskRepository;

    private TodoList courseTodoList;

    @BeforeEach
    void setUp() {
        courseTodoList = new TodoList("Course");
        courseTodoList = entityManager.persist(courseTodoList);
    }

    @Test
    void whenSave_thenReturnTaskList() {
        // Given
        Task expectedTask = new Task(1, "Work", "Work description", PriorityType.HIGH, null, null, courseTodoList);
        Task task = new Task("Work", "Work description", PriorityType.HIGH, courseTodoList);

        // When
        Task taskSaved = taskRepository.save(task);

        // Then
        Assertions.assertThat(taskSaved.getId()).isNotNull();
        Assertions.assertThat(taskSaved).isEqualToIgnoringGivenFields(expectedTask, "id");
        entityManager.remove(taskSaved);
    }

    @Test
    void whenSaveWithoutTodoList_thenThrowException() {
        // Given
        Task task = new Task("Work", "Work description", PriorityType.HIGH, null);

        try {
            // When
            taskRepository.save(task);
            Assertions.fail("Todo list found");
        } catch (Exception e) {
            // Then
            Assertions.assertThat(e).isInstanceOf(DataIntegrityViolationException.class);
        }
    }
}