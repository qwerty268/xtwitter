package com.example.xtwitter.controllers;

import com.example.xtwitter.models.Message;
import com.example.xtwitter.models.User;
import com.example.xtwitter.repositories.MessageRepository;
import com.example.xtwitter.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@Controller
public class MainController {

    private final MessageService messageService;

    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    public MainController(MessageService messageService, MessageRepository repository) {
        this.messageService = messageService;

    }


    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {

        Iterable<Message> messages;
        if (filter != null && !filter.isBlank()) {
            messages = messageService.findMessagesByTag(filter);
        } else {
            filter = "";
            messages = messageService.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("messages", messages);
        return "main";
    }

    @RequestMapping(value = "image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

        File serverFile = new File(uploadPath + "/" + imageName);

        return Files.readAllBytes(serverFile.toPath());
    }


    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag,
                             @RequestParam("file") MultipartFile file, Model model) throws IOException {
        messageService.addMessage(text, tag, user, file);
        return main("", model);
    }

}
