package fr.spring.course.tycoursetodolistapi.service;

import fr.spring.course.tycoursetodolistapi.entity.Task;
import fr.spring.course.tycoursetodolistapi.exception.RecoverableException;
import fr.spring.course.tycoursetodolistapi.exception.TaskNotFoundException;
import fr.spring.course.tycoursetodolistapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Task Service.
 */
@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Integer id) throws TaskNotFoundException {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }
}
