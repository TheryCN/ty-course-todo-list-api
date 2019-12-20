package fr.spring.course.tycoursetodolistapi.repository;

import fr.spring.course.tycoursetodolistapi.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
}
