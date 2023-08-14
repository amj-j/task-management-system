package sk.amjj.taskmanagementsystem.dto.task;

import java.util.Date;

import lombok.Data;

@Data
public class CreateTaskDto {

    private String name;

    private String description;

    private Long categoryId;

    private Date dueDate;

    private Long ownerId;
    
}
