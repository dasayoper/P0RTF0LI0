package ru.itis.eyejust.handlers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.eyejust.exceptions.*;
import ru.itis.eyejust.security.details.UserEntityDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException(UserNotFoundException ex,
                                                    Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "Пользователь с указанным id не найден");
        addCommonAttributes(model);
        return modelAndView;
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ModelAndView handleFileNotFoundException(FileNotFoundException ex,
                                                    Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "Файл с указанным id не найден");
        addCommonAttributes(model);
        return modelAndView;
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ModelAndView handleReportNotFoundException(ReportNotFoundException ex,
                                                      Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "Файл отчета с указанным id не найден.");
        addCommonAttributes(model);
        return modelAndView;
    }

    @ExceptionHandler(AccessIsDeniedException.class)
    public ModelAndView handleAccessIsDeniedException(AccessIsDeniedException ex,
                                                      Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "Извините, у вас недостаточно прав для просмотра данной страницы.");
        addCommonAttributes(model);
        return modelAndView;
    }

    @ExceptionHandler(DiagnosticException.class)
    public ModelAndView handleDiagnosticException(DiagnosticException ex,
                                                  Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "Ошибка диагностики, пожалуйста проверьте правильность входных данных.");
        addCommonAttributes(model);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex,
                                        Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "Произошла ошибка, пожалуйста попробуйте позже.");
        addCommonAttributes(model);
        return modelAndView;
    }

    private void addCommonAttributes(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserEntityDetails) {
            UserEntityDetails userEntityDetails = (UserEntityDetails) principal;
            model.addAttribute("user", userEntityDetails.getUserEntity());
        }
    }
}
