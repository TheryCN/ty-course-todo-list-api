package fr.spring.course.tycoursetodolistapi.controller;

import fr.spring.course.tycoursetodolistapi.dto.TaskDto;
import fr.spring.course.tycoursetodolistapi.entity.Task;
import fr.spring.course.tycoursetodolistapi.exception.TaskNotFoundException;
import fr.spring.course.tycoursetodolistapi.service.TaskService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

/**
 * Test class {@link TaskRestController}.
 */
class TaskRestControllerTest {

    private TaskService taskService;

    private TaskRestController taskRestController;

    @BeforeEach
    void setUp() {
        taskService = Mockito.mock(TaskService.class);
        taskRestController = new TaskRestController(taskService);
    }

    @Test
    void whenGetTasks_thenReturnWorkTaskOnly() {
        // Given
        TaskDto expectedTask = new TaskDto(1, "Work",null, null, null, null);
        Task task = new Task(1, "Work", null, null, null, null, null);
        Mockito.when(taskService.findAll()).thenReturn(Arrays.asList(task));

        // When
        ResponseEntity<List<TaskDto>> responseEntity = taskRestController.getTasks();

        // Then
        Mockito.verify(taskService).findAll();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(Arrays.asList(expectedTask));
    }

    @Test
    void whenGetById_thenReturnTask() throws TaskNotFoundException {
        // Given
        TaskDto expectedTask = new TaskDto(1, "Work",null, null, null, null);
        Task task = new Task(1, "Work", null, null, null, null, null);
        Mockito.when(taskService.findById(1)).thenReturn(task);

        // When
        ResponseEntity<TaskDto> responseEntity = taskRestController.getTaskById(1);

        // Then
        Mockito.verify(taskService).findById(1);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(expectedTask);
    }

    @Test
    void whenGetById_thenReturnTaskNotFound() throws TaskNotFoundException {
        // Given
        Mockito.when(taskService.findById(1)).thenThrow(new TaskNotFoundException());

        // When
        ResponseEntity<TaskDto> responseEntity = taskRestController.getTaskById(1);

        // Then
        Mockito.verify(taskService).findById(1);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}