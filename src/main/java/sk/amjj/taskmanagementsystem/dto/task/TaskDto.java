package sk.amjj.taskmanagementsystem.dto.task;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.amjj.taskmanagementsystem.enums.TaskState;
import sk.amjj.taskmanagementsystem.model.entities.Task;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String name;

    private String description;

    private Long categoryId;

    private Date dueDate;

    private TaskState state;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.categoryId = task.getCategory().getId();
        this.dueDate = task.getDueDate();
        this.state = task.getState();
    }
}
