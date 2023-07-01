package dev.anderson.oauth2spring.controller;

import dev.anderson.oauth2spring.config.UserAnnotation;
import dev.anderson.oauth2spring.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class HtmlController {
    @GetMapping()
    public String index(Model model, @UserAnnotation SessionUser user) {
        if (user != null) {
            model.addAttribute("name", user.name());
            model.addAttribute("email", user.email());
            model.addAttribute("avatar", user.avatarUrl());
        }
        return "index";
    }

}
