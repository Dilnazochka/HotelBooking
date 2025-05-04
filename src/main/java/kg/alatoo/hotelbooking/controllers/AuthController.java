package kg.alatoo.hotelbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";

    }
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password) {
        // Здесь должна быть логика проверки учетных данных
        // Например, вызов AuthService для проверки
        // Если успех, редирект на /home
        return "redirect:/home";
    }


}