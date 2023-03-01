package com.shop.site.service.impl;

import com.shop.site.dto.request.LoginRequest;
import com.shop.site.dto.request.RegisterRequest;
import com.shop.site.dto.response.AuthenticationResponse;
import com.shop.site.model.Role;
import com.shop.site.model.RoleName;
import com.shop.site.model.User;
import com.shop.site.repository.TokenRepository;
import com.shop.site.security.JwtService;
import com.shop.site.service.AuthenticationService;
import com.shop.site.service.RoleService;
import com.shop.site.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private RoleService roleService;
    private TokenRepository tokenRepository;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager,
                                     RoleService roleService,
                                     TokenRepository tokenRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
        this.tokenRepository = tokenRepository;
    }

    @PostConstruct
    private void inject() {
        Role admin = new Role();
        admin.setRoleName(RoleName.ADMIN);
        Role user = new Role();
        user.setRoleName(RoleName.USER);
        roleService.save(admin);
        roleService.save(user);
    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(roleService.findRoleByRoleName(RoleName.USER));
        userService.save(user);
        String token = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        return response;
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));
        User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));
        String userRole = user.getRole().getRoleName().name();
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("roles", userRole);
        String token = jwtService.generateToken(extraClaims, user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        return response;
    }

    @Override
    public void logout(String token) {

        tokenRepository.save(token);
    }
}
