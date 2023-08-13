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

    private String category;

    private Date dueDate;

    private TaskState state;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.category = task.getCategory();
        this.dueDate = task.getDueDate();
        this.state = task.getState();
    }
}
