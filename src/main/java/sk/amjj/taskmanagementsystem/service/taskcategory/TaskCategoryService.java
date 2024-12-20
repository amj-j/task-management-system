package sk.amjj.taskmanagementsystem.service.taskcategory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.amjj.taskmanagementsystem.dto.taskcategory.TaskCategoryDto;
import sk.amjj.taskmanagementsystem.exceptions.IdNotFoundException;
import sk.amjj.taskmanagementsystem.model.entities.TaskCategory;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.model.repository.ITaskCategoryRepository;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskCategoryService;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Service
public class TaskCategoryService implements ITaskCategoryService {
    
    @Autowired
    private ITaskCategoryRepository taskCategoryRepository;

    @Autowired
    private IUserService userService;

    @Override
    public List<TaskCategory> getAllByUser(long userId) throws IdNotFoundException {
        User user = this.userService.getById(userId);
        return taskCategoryRepository.findAllByOwner(user);
    }

    @Override
    public List<TaskCategory> getAllInUseByUser(long userId) throws IdNotFoundException {
        User user = this.userService.getById(userId);
        return taskCategoryRepository.findAllWithTasksForUser(user);
    }

    @Override
    public TaskCategory getById(long id) throws IdNotFoundException {
        Optional<TaskCategory> task = taskCategoryRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        }
        else {
            throw new IdNotFoundException("Task category not found!");
        }
    }

    @Override
    public TaskCategory create(TaskCategoryDto req) throws IdNotFoundException {
        TaskCategory category = new TaskCategory();
        category.setName(req.getName());
        category.setDescription(req.getDescription());

        category.setOwner(this.userService.getById(req.getOwnerId()));
        return this.taskCategoryRepository.save(category);
    }

    @Override
    public TaskCategory update(TaskCategoryDto req) throws IdNotFoundException {
        TaskCategory taskCategory = this.getById(req.getId());
        if (req.getName() != null) {
            taskCategory.setName(req.getName());
        }
        if (req.getDescription() != null) {
            taskCategory.setDescription(req.getDescription());
        }
        return this.taskCategoryRepository.save(taskCategory);
    }

    @Override
    public Long getCountByUser(long userId) throws IdNotFoundException {
        User user = this.userService.getById(userId);
        return this.taskCategoryRepository.countByOwner(user);
    }

    @Override
    public boolean hasTasks(long id) throws IdNotFoundException {
        this.getById(id);
        return this.taskCategoryRepository.existsByIdAndTasksIsNotEmpty(id);
    }

    @Override
    public void delete(long id) throws IdNotFoundException {
        this.taskCategoryRepository.delete(this.getById(id));
    }

    @Override
    public void deleteAllByOwnerId(long userId) throws IdNotFoundException {
        this.taskCategoryRepository.deleteAllByOwner(this.userService.getById(userId));
    }

}
