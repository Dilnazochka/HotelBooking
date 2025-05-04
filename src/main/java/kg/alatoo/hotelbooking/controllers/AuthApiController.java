package kg.alatoo.hotelbooking.controllers;

import kg.alatoo.hotelbooking.dto.AuthRequest;
import kg.alatoo.hotelbooking.dto.AuthResponse;
import kg.alatoo.hotelbooking.dto.RegisterRequest;
import kg.alatoo.hotelbooking.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }
}