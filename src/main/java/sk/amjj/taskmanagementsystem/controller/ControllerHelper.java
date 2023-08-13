package sk.amjj.taskmanagementsystem.controller;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import sk.amjj.taskmanagementsystem.exceptions.UserMissingException;

@Component
public class ControllerHelper { 

    public Long getLoggedInUserId(HttpSession session) throws UserMissingException {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null) {
            throw new UserMissingException();
        }
        return userId;
    }

}
