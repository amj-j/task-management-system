package sk.amjj.taskmanagementsystem.dto.task;

import java.util.Date;

import lombok.Data;

@Data
public class CreateTaskDto {

    private String name;

    private String description;

    private String category;

    private Date dueDate;

    private Long ownerId;
    
}
