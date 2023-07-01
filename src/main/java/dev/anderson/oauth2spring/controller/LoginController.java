package dev.anderson.oauth2spring.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/callback")
    public void callback(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/v1/welcome");
    }
}
