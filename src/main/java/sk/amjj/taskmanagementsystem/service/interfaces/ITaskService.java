package sk.amjj.taskmanagementsystem.service.interfaces;

import java.util.List;

import sk.amjj.taskmanagementsystem.dto.task.CreateTaskDto;
import sk.amjj.taskmanagementsystem.dto.task.TaskDto;
import sk.amjj.taskmanagementsystem.enums.SortDirection;
import sk.amjj.taskmanagementsystem.enums.SortTasksBy;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.model.entities.Task;

public interface ITaskService {
    
    Task create(CreateTaskDto req) throws NotFoundException;

    Task getById(long id) throws NotFoundException;

    Task update(TaskDto req) throws NotFoundException;

    void sort(List<Task> tasks, SortTasksBy sortBy, SortDirection sortDir);

    void delete(long id) throws NotFoundException;

    void deleteAllByOwnerId(long userId) throws NotFoundException;

}
