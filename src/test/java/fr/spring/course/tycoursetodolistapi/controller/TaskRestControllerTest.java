package fr.spring.course.tycoursetodolistapi.controller;

import fr.spring.course.tycoursetodolistapi.dto.TaskDto;
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
        TaskDto expectedTask = new TaskDto(1, "Work",null, null, null, null);

        // When
        ResponseEntity<List<TaskDto>> responseEntity = taskRestController.getTasks();

        // Then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(Arrays.asList(expectedTask));
    }
}