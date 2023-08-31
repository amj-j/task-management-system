package sk.amjj.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.dto.taskcategory.TaskCategoryDto;
import sk.amjj.taskmanagementsystem.dto.user.UserRegistrationDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.PasswordsDoNotMatchException;
import sk.amjj.taskmanagementsystem.exceptions.UsernameTakenException;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.service.interfaces.ITaskCategoryService;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Controller
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskCategoryService taskCategoryService;


    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @ModelAttribute("user") UserRegistrationDto regDto, 
            HttpSession session, 
            Model model) throws NotFoundException {
        try {
            User newUser = userService.create(regDto);
            session.setAttribute("loggedInUserId", newUser.getId());
            this.taskCategoryService.create(new TaskCategoryDto("Default", "Default category", newUser.getId()));
            return "redirect:/home";
        }
        catch(UsernameTakenException e) {
            model.addAttribute("usernameUnavailable", true);
            model.addAttribute("user", regDto);
            return "registration";
        }
        catch(PasswordsDoNotMatchException e) {
            model.addAttribute("passwordsDoNotMatch", true);
            model.addAttribute("user", regDto);
            return "registration";
        }
    }

}
