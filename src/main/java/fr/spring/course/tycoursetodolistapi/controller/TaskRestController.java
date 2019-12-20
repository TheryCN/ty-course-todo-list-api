package fr.spring.course.tycoursetodolistapi.controller;

import fr.spring.course.tycoursetodolistapi.aop.LogEachCall;
import fr.spring.course.tycoursetodolistapi.dto.TaskDto;
import fr.spring.course.tycoursetodolistapi.entity.Task;
import fr.spring.course.tycoursetodolistapi.exception.TaskNotFoundException;
import fr.spring.course.tycoursetodolistapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Task Rest Controller.
 */
@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    private TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @LogEachCall
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<TaskDto> taskList = taskService.findAll().stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{id}")
    @LogEachCall
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(toDto(taskService.findById(id)));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getName(), task.getDescription(), task.getPriority(), task.getStartDate(), task.getEndDate());
    }

}
