package sk.amjj.taskmanagementsystem.service.interfaces;

import sk.amjj.taskmanagementsystem.dto.task.CreateTaskDto;
import sk.amjj.taskmanagementsystem.dto.task.TaskDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.model.entities.Task;

public interface ITaskService {
    
    Task create(CreateTaskDto req) throws NotFoundException;

    Task getById(long id) throws NotFoundException;

    Task update(TaskDto req) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    void deleteAllByOwnerId(long userId) throws NotFoundException;

}
