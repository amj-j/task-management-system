package sk.amjj.taskmanagementsystem.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserMissingException.class)
    public String handleAuthenticationRequiredException(UserMissingException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("nooneLoggedInMessage", ex.getMessage());
        return "redirect:/login";
    }
}
