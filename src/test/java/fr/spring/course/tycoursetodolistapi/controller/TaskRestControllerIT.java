package fr.spring.course.tycoursetodolistapi.controller;

import fr.spring.course.tycoursetodolistapi.dto.TaskDto;
import fr.spring.course.tycoursetodolistapi.entity.PriorityType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskRestControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    TaskDto prepareSlidesTask, prepareExerciceTask, wakeUpTask;

    @BeforeEach
    void setUp() {
        LocalDateTime christmasLocalDateTime = LocalDateTime.of(2019, 12, 25, 0, 0, 0);
        Date christmasDate = Date.from(christmasLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());

        prepareSlidesTask = new TaskDto(1, "Prepare Slides", "Do it on Google Slide", PriorityType.MEDIUM, christmasDate, null);
        prepareExerciceTask = new TaskDto(2, "Prepare Exercice", "Do a web project with steps", PriorityType.MEDIUM, christmasDate, null);
        wakeUpTask = new TaskDto(3, "Wake up at 8am", "Be at office on time", PriorityType.HIGH, christmasDate, null);
    }

    @Test
    void whenGetTasks_thenReturnTaskList() {
        // Given
        ParameterizedTypeReference<List<TaskDto>> parameterizedTypeReference = new ParameterizedTypeReference<List<TaskDto>>() {
        };

        // When
        ResponseEntity<List<TaskDto>> responseEntity = testRestTemplate.exchange("/tasks", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders("Thery", "ChangeIt")), parameterizedTypeReference);

        // Then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).contains(prepareSlidesTask, prepareExerciceTask, wakeUpTask);
    }

    @Test
    void whenGetTaskById_thenReturnTask() {
        // Given
        // When
        ResponseEntity<TaskDto> responseEntity = testRestTemplate.exchange("/tasks/1", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders("Thery", "ChangeIt")), TaskDto.class);

        // Then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(prepareSlidesTask);
    }

    @Test
    void whenGetTaskById_thenReturnTaskNotFound() {
        // Given
        // When
        ResponseEntity<TaskDto> responseEntity = testRestTemplate.exchange("/tasks/10", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders("Thery", "ChangeIt")), TaskDto.class);

        // Then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}