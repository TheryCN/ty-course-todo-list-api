package fr.spring.course.tycoursetodolistapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.spring.course.tycoursetodolistapi.entity.PriorityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Integer id;

    private String name;

    private String description;

    private PriorityType priority;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
}
