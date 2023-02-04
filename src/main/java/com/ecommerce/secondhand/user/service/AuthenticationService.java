package com.ecommerce.secondhand.user.service;



import com.ecommerce.secondhand.config.JwtService;
import com.ecommerce.secondhand.user.model.dto.AuthenticationResponse;
import com.ecommerce.secondhand.user.model.dto.CreateLoginRequest;
import com.ecommerce.secondhand.user.model.dto.CreateUserRequest;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(CreateLoginRequest createLoginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        createLoginRequest.getMail(),
                        createLoginRequest.getPassword()
                )
        );

        var user = userRepository.findByMail(createLoginRequest.getMail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken);

        return authenticationResponse;

    }

    public AuthenticationResponse register(CreateUserRequest createUserRequest) {

    User user =
        new User(
            createUserRequest.getMail(),
            createUserRequest.getFirstName(),
            createUserRequest.getLastName(),
            createUserRequest.getMiddleName(),
            false,
            passwordEncoder.encode(createUserRequest.getPassword()));

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken);

        return authenticationResponse;

    }
}
