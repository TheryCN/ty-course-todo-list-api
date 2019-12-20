package fr.spring.course.tycoursetodolistapi.repository;

import fr.spring.course.tycoursetodolistapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
