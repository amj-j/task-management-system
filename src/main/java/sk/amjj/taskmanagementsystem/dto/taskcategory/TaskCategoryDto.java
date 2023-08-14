package sk.amjj.taskmanagementsystem.dto.taskcategory;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.amjj.taskmanagementsystem.model.entities.TaskCategory;

@Data
@NoArgsConstructor
public class TaskCategoryDto {

    private Long id;
    
    private String name;

    private String description;

    private Long ownerId;

    public TaskCategoryDto(TaskCategory taskCategory) {
        this.id = taskCategory.getId();
        this.name = taskCategory.getName();
        this.description = taskCategory.getDescription();
        this.ownerId = taskCategory.getOwner().getId();
    }

    public TaskCategoryDto(String name, String description, long ownerId) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }

}
