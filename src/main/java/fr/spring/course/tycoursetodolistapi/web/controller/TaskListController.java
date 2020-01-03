package fr.spring.course.tycoursetodolistapi.web.controller;

import fr.spring.course.tycoursetodolistapi.controller.TaskRestController;
import fr.spring.course.tycoursetodolistapi.dto.TaskDto;
import fr.spring.course.tycoursetodolistapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui/tasks")
public class TaskListController {

    private TaskRestController taskRestController;

    public TaskListController(TaskRestController taskRestController) {
        this.taskRestController = taskRestController;
    }

    @GetMapping
    public String tasks(Model model) {
        ResponseEntity<List<TaskDto>> responseEntity = taskRestController.getTasks();
        List<TaskDto> taskDtoList = responseEntity.getBody();

        model.addAttribute("taskDtoList", taskDtoList);
        return "tasks";
    }

}
