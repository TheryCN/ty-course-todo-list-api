package fr.spring.course.tycoursetodolistapi.controller;

import fr.spring.course.tycoursetodolistapi.dto.TaskDto;
import fr.spring.course.tycoursetodolistapi.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskRestControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void whenGetTasks_thenReturnWorkTaskOnly() {
        // Given
        TaskDto expectedTask = new TaskDto(1, "Work", null, null, null, null);
        ParameterizedTypeReference<List<TaskDto>> parameterizedTypeReference = new ParameterizedTypeReference<List<TaskDto>>() {
        };

        // When
        ResponseEntity<List<TaskDto>> responseEntity = testRestTemplate.exchange("/tasks", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders("Thery","ChangeIt")), parameterizedTypeReference);

        // Then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(Arrays.asList(expectedTask));
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