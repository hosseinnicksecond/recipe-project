package home.train.controller;

import home.train.exception.ExceptionHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExceptionHandle.class)
    public ModelAndView exceptionPage(Exception exception){
        log.error("handle Not Found Exception");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("404error");
        mv.addObject("exception",exception);
        return mv;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception exception){
        log.error(exception.getMessage());
        ModelAndView mv= new ModelAndView();
        mv.setViewName("400error");
        mv.addObject("exception",exception);
        return mv;
    }
}
