package sk.amjj.taskmanagementsystem.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserMissingException.class)
    public ModelAndView handleUserMissingException(UserMissingException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ModelAndView handleNotFoundException(IdNotFoundException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", ex.getMessage());
        return mav;
    }

}
