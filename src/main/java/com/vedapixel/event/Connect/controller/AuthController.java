package com.vedapixel.event.Connect.controller;

import com.vedapixel.event.Connect.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        String token = authService.login(
                request.get("username"),
                request.get("password")
        );

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}

