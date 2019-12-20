package fr.spring.course.tycoursetodolistapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Task extends AbstractAuditingEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "todo_list_id", foreignKey = @ForeignKey(name = "fk_todo_list_id"))
    private TodoList todoList;

    public Task(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Task(String name, String description, PriorityType priority, TodoList todoList) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.todoList = todoList;
    }

}
