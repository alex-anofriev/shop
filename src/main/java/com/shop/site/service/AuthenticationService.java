package com.shop.site.service;

import com.shop.site.dto.request.LoginRequest;
import com.shop.site.dto.request.RegisterRequest;
import com.shop.site.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);
}
