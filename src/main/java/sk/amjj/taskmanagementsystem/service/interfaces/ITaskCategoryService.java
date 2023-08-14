package sk.amjj.taskmanagementsystem.service.interfaces;

import java.util.List;

import sk.amjj.taskmanagementsystem.dto.taskcategory.TaskCategoryDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.model.entities.TaskCategory;

public interface ITaskCategoryService {

    List<TaskCategory> getAllByUser(Long userId) throws NotFoundException;

    List<TaskCategory> getAllInUseByUser(Long userId) throws NotFoundException;

    TaskCategory getById(Long id) throws NotFoundException;

    TaskCategory create(TaskCategoryDto req) throws NotFoundException;

    TaskCategory update(TaskCategoryDto req) throws NotFoundException;

    void delete(long id) throws NotFoundException;

}
