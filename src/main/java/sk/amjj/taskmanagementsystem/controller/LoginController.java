package sk.amjj.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.dto.user.UserLoginDto;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

@Controller
public class LoginController {
    
    @Autowired
    private IUserService userService;
    

    @ModelAttribute("loginInfo") 
    private UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public String showLoginPage( HttpSession session) {
        if (session.getAttribute("loggedInUserId") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginInfo") UserLoginDto loginInfo, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = this.userService.authenticate(loginInfo);
        if (user == null) {
            redirectAttributes.addFlashAttribute("invalidData", true);
            return "redirect:/login";
        }
        else {
            session.setAttribute("loggedInUserId", user.getId());
            return "redirect:/home";
        }
    }
 
}
