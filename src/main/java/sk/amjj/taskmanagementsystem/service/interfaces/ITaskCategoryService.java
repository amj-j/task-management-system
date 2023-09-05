package sk.amjj.taskmanagementsystem.service.interfaces;

import java.util.List;

import sk.amjj.taskmanagementsystem.dto.taskcategory.TaskCategoryDto;
import sk.amjj.taskmanagementsystem.exceptions.IdNotFoundException;
import sk.amjj.taskmanagementsystem.model.entities.TaskCategory;

public interface ITaskCategoryService {

    List<TaskCategory> getAllByUser(long userId) throws IdNotFoundException;

    List<TaskCategory> getAllInUseByUser(long userId) throws IdNotFoundException;

    TaskCategory getById(long id) throws IdNotFoundException;

    TaskCategory create(TaskCategoryDto req) throws IdNotFoundException;

    TaskCategory update(TaskCategoryDto req) throws IdNotFoundException;

    Long getCountByUser(long userId) throws IdNotFoundException;

    boolean hasTasks(long id) throws IdNotFoundException;

    void delete(long id) throws IdNotFoundException;

    void deleteAllByOwnerId(long userId) throws IdNotFoundException;

}
