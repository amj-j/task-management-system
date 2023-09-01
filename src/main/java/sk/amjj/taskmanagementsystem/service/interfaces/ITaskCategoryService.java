package sk.amjj.taskmanagementsystem.service.interfaces;

import java.util.List;

import sk.amjj.taskmanagementsystem.dto.taskcategory.TaskCategoryDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.model.entities.TaskCategory;

public interface ITaskCategoryService {

    List<TaskCategory> getAllByUser(long userId) throws NotFoundException;

    List<TaskCategory> getAllInUseByUser(long userId) throws NotFoundException;

    TaskCategory getById(long id) throws NotFoundException;

    TaskCategory create(TaskCategoryDto req) throws NotFoundException;

    TaskCategory update(TaskCategoryDto req) throws NotFoundException;

    Long getCountByUser(long userId) throws NotFoundException;

    boolean hasTasks(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    void deleteAllByOwnerId(long userId) throws NotFoundException;

}
