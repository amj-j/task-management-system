package sk.amjj.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.dto.user.UserInfoDto;
import sk.amjj.taskmanagementsystem.dto.user.UserUpdateDto;
import sk.amjj.taskmanagementsystem.exceptions.IdNotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.UserMissingException;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskCategoryService;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskService;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private IUserService userService;

    
    @Autowired
    private ITaskService taskService;

    @Autowired
    private ITaskCategoryService taskCategoryService;

    @Autowired
    private ControllerHelper controllerHelper;


    @GetMapping
    public ModelAndView showUserDetails(HttpSession session) throws IdNotFoundException, UserMissingException {
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        UserInfoDto userDto = new UserInfoDto(userService.getById(userId));
        ModelAndView mav = new ModelAndView("user/user-details");
        mav.addObject("user", userDto);
        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView showUserUpdateForm(HttpSession session) throws IdNotFoundException, UserMissingException {
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        UserUpdateDto dto = new UserUpdateDto(userService.getById(userId));
        ModelAndView mav = new ModelAndView("user/edit-user-form");
        mav.addObject("user", dto);
        return mav;
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute UserUpdateDto dto, HttpSession session) throws IdNotFoundException, UserMissingException {
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        this.userService.update(userId, dto);
        return "redirect:/user";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("loggedInUserId");
        return "redirect:/login";
    }

    @GetMapping("/delete")
    public String deleteTask(HttpSession session) throws IdNotFoundException {
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        session.removeAttribute("loggedInUserId");
        this.taskService.deleteAllByOwnerId(userId);
        this.taskCategoryService.deleteAllByOwnerId(userId);
        this.userService.delete(userId);
        return "redirect:/login";
    }
}
