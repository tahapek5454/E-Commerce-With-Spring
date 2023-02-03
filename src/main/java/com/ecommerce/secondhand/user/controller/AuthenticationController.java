package com.ecommerce.secondhand.user.controller;

import com.ecommerce.secondhand.user.model.dto.AuthenticationResponse;
import com.ecommerce.secondhand.user.model.dto.CreateLoginRequest;
import com.ecommerce.secondhand.user.model.dto.CreateUserRequest;
import com.ecommerce.secondhand.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(authenticationService.register(createUserRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody CreateLoginRequest createLoginRequest){
        return ResponseEntity.ok(authenticationService.authenticate(createLoginRequest));

    }
}
