package sk.amjj.taskmanagementsystem.service.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.amjj.taskmanagementsystem.comparators.TaskComparator;
import sk.amjj.taskmanagementsystem.dto.task.CreateTaskDto;
import sk.amjj.taskmanagementsystem.dto.task.TaskDto;
import sk.amjj.taskmanagementsystem.enums.SortDirection;
import sk.amjj.taskmanagementsystem.enums.SortTasksBy;
import sk.amjj.taskmanagementsystem.enums.TaskState;
import sk.amjj.taskmanagementsystem.exceptions.IdNotFoundException;
import sk.amjj.taskmanagementsystem.model.entities.Task;
import sk.amjj.taskmanagementsystem.model.repository.ITaskRepository;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskCategoryService;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskService;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Service
public class TaskService implements ITaskService {
    
    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskCategoryService taskCategoryService;

    @Override
    public Task create(CreateTaskDto req) throws IdNotFoundException {
        Task task = new Task();
        task.setName(req.getName());
        task.setDescription(req.getDescription());
        task.setCategory(this.taskCategoryService.getById(req.getCategoryId()));
        task.setDueDate(req.getDueDate());
        task.setState(TaskState.TODO);

        task.setOwner(this.userService.getById(req.getOwnerId()));
        return this.taskRepository.save(task);
    }

    @Override
    public Task getById(long id) throws IdNotFoundException {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        }
        else {
            throw new IdNotFoundException("Task not found!");
        }
    }

    @Override
    public Task update(TaskDto req) throws IdNotFoundException {
        Task task = this.getById(req.getId());
        if (req.getName() != null) {
            task.setName(req.getName());
        }
        if (req.getDescription() != null) {
            task.setDescription(req.getDescription());
        }
        if (req.getCategoryId() != null) {
            task.setCategory(this.taskCategoryService.getById(req.getCategoryId()));
        }
        if (req.getDueDate() != null) {
            task.setDueDate(req.getDueDate());
        }
        if (req.getState() != null) {
            task.setState(req.getState());
        }
        return this.taskRepository.save(task);
    }

    @Override
    public void sort(List<Task> tasks, SortTasksBy sortBy, SortDirection sortDir) {
        TaskComparator comparator = new TaskComparator(sortBy, sortDir);
        tasks.sort(comparator);
    }

    @Override
    public void delete(long id) throws IdNotFoundException {
        this.taskRepository.delete(this.getById(id));
    }

    @Override
    public void deleteAllByOwnerId(long userId) throws IdNotFoundException {
        this.taskRepository.deleteAllByOwner(this.userService.getById(userId));
    }

}
