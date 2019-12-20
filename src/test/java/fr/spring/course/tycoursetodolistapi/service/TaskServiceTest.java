package fr.spring.course.tycoursetodolistapi.service;

import fr.spring.course.tycoursetodolistapi.entity.Task;
import fr.spring.course.tycoursetodolistapi.exception.TaskNotFoundException;
import fr.spring.course.tycoursetodolistapi.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Test class {@link TaskService}.
 */
class TaskServiceTest {

    private TaskRepository taskRepository;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void whenFindAll_thenReturnTaskList() {
        // Given
        Task task = new Task(1, "Work", null, null, null, null, null);

        Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(task));

        // When
        List<Task> taskList = taskService.findAll();

        // Then
        Mockito.verify(taskRepository).findAll();
        Assertions.assertThat(taskList).containsExactly(task);
    }

    @Test
    void whenFindById_thenReturnTask() throws TaskNotFoundException {
        // Given
        Task expectedTask = new Task(1, "Work", null, null, null, null, null);

        Mockito.when(taskRepository.findById(1)).thenReturn(Optional.of(expectedTask));

        // When
        Task task = taskService.findById(1);

        // Then
        Mockito.verify(taskRepository).findById(1);
        Assertions.assertThat(task).isEqualTo(expectedTask);
    }

    @Test
    void whenFindById_thenThrowTaskNotFoundException() {
        // Given
        Mockito.when(taskRepository.findById(1)).thenReturn(Optional.empty());

        // When
        try {
            taskService.findById(1);
            Assertions.fail("Expect TaskNotFoundException");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(TaskNotFoundException.class);
        }
    }
}