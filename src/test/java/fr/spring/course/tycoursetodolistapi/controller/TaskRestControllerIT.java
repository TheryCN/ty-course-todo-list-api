package fr.spring.course.tycoursetodolistapi.controller;

import fr.spring.course.tycoursetodolistapi.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskRestControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void whenGetTasks_thenReturnWorkTaskOnly() {
        // Given
        Task expectedTask = new Task(1, "Work");
        ParameterizedTypeReference<List<Task>> parameterizedTypeReference = new ParameterizedTypeReference<List<Task>>() {
        };

        // When
        ResponseEntity<List<Task>> responseEntity = testRestTemplate.exchange("/tasks", HttpMethod.GET, null, parameterizedTypeReference);

        // Then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(Arrays.asList(expectedTask));
    }
}