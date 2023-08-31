package sk.amjj.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.PasswordsDoNotMatchException;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Controller
@RequestMapping("/user/changePassword")
public class ChangeUserPasswordController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ControllerHelper controllerHelper;
    

    @GetMapping("/verifyCurrent")
    public String showVerifyCurrentPasswordPage() {
        return "user/password-verification";
    }

    @PostMapping("/verifyCurrent")
    public String verifyPasswordBeforeChanging(@RequestParam("password") String password, HttpSession session, Model model) throws NotFoundException {
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        if (this.userService.verifyPassword(userId, password)) {
            return "user/change-password";
        }
        else {
            model.addAttribute("incorrectPassword", true);
            return "user/password-verification";
        }
    }

    @GetMapping
    public String showChangePasswordPage() {
        return "user/change-password";
    }

    @PostMapping
    public String updatePassword(@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, HttpSession session, Model model) throws NotFoundException {
        Long userId = this.controllerHelper.getLoggedInUserId(session);
        try {
            this.userService.updatePassword(userId, password, confirmPassword);
        }
        catch(PasswordsDoNotMatchException ex) {
            model.addAttribute("doNotMatch", true);
            return "user/change-password";
        }
        return "redirect:/home";
    }
}
