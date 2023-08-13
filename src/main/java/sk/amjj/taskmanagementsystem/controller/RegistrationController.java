package sk.amjj.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.dto.user.UserRegistrationDto;
import sk.amjj.taskmanagementsystem.exceptions.PasswordsDoNotMatchException;
import sk.amjj.taskmanagementsystem.exceptions.UsernameTakenException;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.service.user.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

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
            Model model) {
        try {
            User newUser = userService.create(regDto);
            session.setAttribute("loggedInUserId", newUser.getId());
            return "redirect:/home";
        }
        catch(UsernameTakenException e) {
            model.addAttribute("errorMessage", "This username is unavailable!");
            model.addAttribute("user", regDto);
            return "registration";
        }
        catch(PasswordsDoNotMatchException e) {
            model.addAttribute("errorMessage", "Passwords do not match!");
            model.addAttribute("user", regDto);
            return "registration";
        }
    }

}
