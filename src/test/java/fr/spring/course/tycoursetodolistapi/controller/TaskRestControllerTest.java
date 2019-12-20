package fr.spring.course.tycoursetodolistapi.controller;

import fr.spring.course.tycoursetodolistapi.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

class TaskRestControllerTest {

    private TaskRestController taskRestController;

    @BeforeEach
    void setUp() {
        taskRestController = new TaskRestController();
    }

    @Test
    void whenGetTasks_thenReturnWorkTaskOnly() {
        // Given
        Task expectedTask = new Task(1, "Work");

        // When
        ResponseEntity<List<Task>> responseEntity = taskRestController.getTasks();

        // Then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(Arrays.asList(expectedTask));
    }
}