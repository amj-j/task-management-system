package sk.amjj.taskmanagementsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.dto.task.TaskDto;
import sk.amjj.taskmanagementsystem.dto.taskcategory.TaskCategoryDto;
import sk.amjj.taskmanagementsystem.enums.SortDirection;
import sk.amjj.taskmanagementsystem.enums.SortTasksBy;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.UserMissingException;
import sk.amjj.taskmanagementsystem.model.entities.Task;
import sk.amjj.taskmanagementsystem.model.entities.TaskCategory;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskCategoryService;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskService;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Controller
public class MainController {
    
    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ITaskCategoryService taskCategoryService;

    @Autowired
    private ControllerHelper controllerHelper;
    

    @GetMapping("/home")
    public ModelAndView showHomePage(
        HttpSession session, 
        @RequestParam(name = "sortBy", defaultValue = "NAME") SortTasksBy sortBy,
        @RequestParam(name = "sortDir", defaultValue = "ASC") SortDirection sortDir) 
        throws NotFoundException, UserMissingException {

        ModelAndView mav = new ModelAndView("home-page");
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        User user = userService.getById(userId);
        this.taskService.sort(user.getTasks(), sortBy, sortDir);

        List<TaskDto> tasks = new ArrayList<>();
        for (Task task : user.getTasks()) {
            tasks.add(new TaskDto(task));
        }

        Map<Long, TaskCategoryDto> taskCategories = new HashMap<>();
        for (TaskCategory category : this.taskCategoryService.getAllInUseByUser(userId)) {
            taskCategories.put(category.getId(), new TaskCategoryDto(category));
        }

        mav.addObject("firstName", user.getFirstName());
        mav.addObject("lastName", user.getLastName());
        mav.addObject("tasks", tasks);
        mav.addObject("taskCategoryMap", taskCategories);
        mav.addObject("sortBy", sortBy);
        mav.addObject("sortDir", sortDir.name());        
        return mav;
    }

}
