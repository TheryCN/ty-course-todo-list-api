package fr.spring.course.tycoursetodolistapi.controller;

import fr.spring.course.tycoursetodolistapi.aop.LogEachCall;
import fr.spring.course.tycoursetodolistapi.dto.TaskDto;
import fr.spring.course.tycoursetodolistapi.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Task Rest Controller.
 */
@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    @GetMapping
    @LogEachCall
    public ResponseEntity<List<TaskDto>> getTasks() {
        return ResponseEntity.ok(Arrays.asList(toDto(new Task(1, "Work"))));
    }

    private TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getName(), task.getDescription(), task.getPriority(), task.getStartDate(), task.getEndDate());
    }

}
