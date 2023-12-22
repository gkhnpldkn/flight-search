package org.gokhan.service;

import org.gokhan.dto.request.SignUpRequest;
import org.gokhan.dto.request.SigninRequest;
import org.gokhan.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signin(SigninRequest request);

    JwtAuthenticationResponse signup(SignUpRequest request);
}
