package com.smartlibrary.authservice;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginRequest request) {
        boolean ok = "admin".equals(request.username()) && "admin123".equals(request.password());
        if (!ok) throw new RuntimeException("Invalid credentials");
        return Map.of("token", "demo-admin-token", "role", "ADMIN", "username", "admin");
    }
    public record LoginRequest(String username, String password) {}
}
