package sk.amjj.taskmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.dto.task.TaskDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.UserMissingException;
import sk.amjj.taskmanagementsystem.model.entities.Task;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Controller
public class MainController {
    
    @Autowired
    private IUserService userService;

    @Autowired
    private ControllerHelper controllerHelper;

    @GetMapping("/home")
    public ModelAndView showHomePage(HttpSession session) throws NotFoundException, UserMissingException {
        ModelAndView mav = new ModelAndView("home-page");
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        User user = userService.getById(userId);
        List<TaskDto> tasks = new ArrayList<>();
        for (Task task : user.getTasks()) {
            tasks.add(new TaskDto(task));
        }
        mav.addObject("firstName", user.getFirstName());
        mav.addObject("lastName", user.getLastName());
        mav.addObject("tasks", tasks);
        return mav;
    }

}
