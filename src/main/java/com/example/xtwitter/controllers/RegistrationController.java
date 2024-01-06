package com.example.xtwitter.controllers;

import com.example.xtwitter.models.User;
import com.example.xtwitter.exceptions.UserAlreadyExistsException;
import com.example.xtwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, RedirectAttributes redirAttrs) {
        try {
            userService.creteNewUser(user);
        } catch (UserAlreadyExistsException e) {
            redirAttrs.addFlashAttribute("error", "Пользователь стаким именем уже существует");
            return "redirect:/registration";
        }
        return "redirect:/login";
    }


    @ResponseStatus(value = HttpStatus.CONFLICT,
            reason = "Пользователь с таким именем уже существует")
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String showAlert(RedirectAttributes redirAttrs, Model model) {
        redirAttrs.addFlashAttribute("error", "Пользователь стаким именем уже существует");
        model.addAttribute("error", "Пользователь стаким именем уже существует");
        return "registration";
    }
}
