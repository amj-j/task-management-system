package sk.amjj.taskmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.dto.taskcategory.TaskCategoryDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.UserMissingException;
import sk.amjj.taskmanagementsystem.model.entities.TaskCategory;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskCategoryService;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Controller
@RequestMapping("/task-category")
public class TaskCategoryController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private ITaskCategoryService taskCategoryService;

    @Autowired
    private ControllerHelper controllerHelper;


    @ModelAttribute("newTaskCategory")
    public TaskCategoryDto taskCategoryRequest() {
        return new TaskCategoryDto();
    }

    @GetMapping("/list")
    public ModelAndView listCategories(HttpSession session) throws NotFoundException, UserMissingException {
        ModelAndView mav = new ModelAndView("task-category/list-categories");
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        User user = userService.getById(userId);

        List<TaskCategoryDto> taskCategories = new ArrayList<>();
        for (TaskCategory category : user.getTaskCategories()) {
            taskCategories.add(new TaskCategoryDto(category));
        }

        mav.addObject("taskCategories", taskCategories);
        return mav;
    }

    @GetMapping("/new")
    public String showAddTaskCategoryForm() {
        return "task-category/add-category-form";
    }

    @PostMapping("/add")
    public String addTaskCategory(@ModelAttribute("newTaskCategory") TaskCategoryDto req, HttpSession session) throws NotFoundException, UserMissingException {
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        req.setOwnerId(userId);
        this.taskCategoryService.create(req);
        return "redirect:/task-category/list";
    }

    @GetMapping("/edit")
    public ModelAndView showEditTaskCategoryForm(@RequestParam Long taskCategoryId) throws NotFoundException {
        ModelAndView mav = new ModelAndView("task-category/edit-category-form");
        TaskCategoryDto taskCategoryResponse = new TaskCategoryDto(taskCategoryService.getById(taskCategoryId));
        mav.addObject("taskCategory", taskCategoryResponse);
        return mav;
    }

    @PostMapping("/update")
    public String updateTaskCategory(@ModelAttribute("taskCategory") TaskCategoryDto taskCategory) throws NotFoundException {
        this.taskCategoryService.update(taskCategory);
        return "redirect:/task-category/list";
    }

    @GetMapping("/details")
    public ModelAndView showTaskCategoryDetails(@RequestParam Long taskCategoryId) throws NotFoundException {
        ModelAndView mav = new ModelAndView("task-category/category-details");
        TaskCategoryDto taskCategoryResponse = new TaskCategoryDto(taskCategoryService.getById(taskCategoryId));
        mav.addObject("taskCategory", taskCategoryResponse);
        return mav;
    }

    @GetMapping("/delete")
    public String deleteTaskCategory(@RequestParam Long taskCategoryId) throws NotFoundException {
        this.taskCategoryService.delete(taskCategoryId);
        return "redirect:/task-category/list";
    }

}
