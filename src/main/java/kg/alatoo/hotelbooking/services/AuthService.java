package kg.alatoo.hotelbooking.services;

import kg.alatoo.hotelbooking.dto.AuthRequest;
import kg.alatoo.hotelbooking.dto.AuthResponse;
import kg.alatoo.hotelbooking.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
}