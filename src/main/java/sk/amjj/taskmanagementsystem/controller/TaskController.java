package sk.amjj.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.dto.task.CreateTaskDto;
import sk.amjj.taskmanagementsystem.dto.task.TaskDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.UserMissingException;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ControllerHelper controllerHelper;
    
    @ModelAttribute("newTask")
    public CreateTaskDto taskRequest() {
        return new CreateTaskDto();
    }

    @GetMapping("/new")
    public String showAddTaskForm() {
        return "add-task-form";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute("newTask") CreateTaskDto req, HttpSession session) throws UserMissingException {
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        req.setOwnerId(userId);
        this.taskService.create(req);
        return "redirect:/home";
    }

    @GetMapping("/edit")
    public ModelAndView showEditTaskForm(@RequestParam Long taskId) throws NotFoundException {
        ModelAndView mav = new ModelAndView("edit-task-form");
        TaskDto taskResponse = new TaskDto(taskService.getById(taskId));
        mav.addObject("task", taskResponse);
        return mav;
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute("task") TaskDto task) throws NotFoundException {
        this.taskService.update(task);
        return "redirect:/home";
    }

    @GetMapping("/details")
    public ModelAndView showTaskDetails(@RequestParam Long taskId) throws NotFoundException {
        ModelAndView mav = new ModelAndView("task-details");
        TaskDto taskResponse = new TaskDto(taskService.getById(taskId));
        mav.addObject("task", taskResponse);
        return mav;
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam Long taskId) throws NotFoundException {
        this.taskService.delete(taskId);
        return "redirect:/home";
    }
}
