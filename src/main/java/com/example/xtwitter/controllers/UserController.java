package com.example.xtwitter.controllers;

import com.example.xtwitter.models.Role;
import com.example.xtwitter.models.User;
import com.example.xtwitter.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getListOfUsersPage(Model model) {
        model.addAttribute("users", userService.findAll());

        return "userList";
    }

    @GetMapping("/{user}")
    public String getEditUserPage(@PathVariable User user, Model model) {
        log.info(user.toString());
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/update")
    public String userUpdate(@RequestParam("userId") User user, @RequestParam Map<String, String> formParams) {
        userService.updateUser(user, formParams);
        return "redirect:/users";
    }

}
