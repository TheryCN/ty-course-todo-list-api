package fr.spring.course.tycoursetodolistapi.web.controller;

import fr.spring.course.tycoursetodolistapi.controller.TaskRestController;
import fr.spring.course.tycoursetodolistapi.dto.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskListController.class)
class TaskListControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskRestController taskRestController;

    @Test
    void whenNavigateToUiTasks_thenCheckPage() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1, "Work", null, null, null, null);
        given(taskRestController.getTasks()).willReturn(ResponseEntity.ok(Arrays.asList(taskDto)));

        // When + Then
        this.mvc.perform(get("/ui/tasks").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//table/tbody/tr").nodeCount(1));
    }
}