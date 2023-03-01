package com.shop.site.controller;

import com.shop.site.dto.request.LoginRequest;
import com.shop.site.dto.request.RegisterRequest;
import com.shop.site.dto.response.AuthenticationResponse;
import com.shop.site.model.Token;
import com.shop.site.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> success() {
        return ResponseEntity.ok("This is login page");
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Token token, HttpServletResponse response) throws IOException {
        authenticationService.logout(token.getToken());
        response.sendRedirect("/api/auth/login");
    }
}
