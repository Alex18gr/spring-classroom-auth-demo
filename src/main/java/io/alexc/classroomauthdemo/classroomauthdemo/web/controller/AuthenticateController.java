package io.alexc.classroomauthdemo.classroomauthdemo.web.controller;

import io.alexc.classroomauthdemo.classroomauthdemo.security.JwtTokenService;
import io.alexc.classroomauthdemo.classroomauthdemo.web.rest.JwtToken;
import io.alexc.classroomauthdemo.classroomauthdemo.web.rest.LoginCredentials;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticateController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenService jwtTokenService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtToken> authenticate(@Valid @RequestBody LoginCredentials login) {
        // create the authentication token based on the provided username and password
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUsername(),
                login.getPassword()
        );

        // create the authentication manager dynamically and authenticate the user based
        // on the received credentials
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // set the authentication result at the spring security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // create the JWT key
        String jwt = jwtTokenService.createToken(authentication, login.getRememberMe());

        // put the generated key in the return http headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwt);
        return new ResponseEntity<>(new JwtToken(jwt), httpHeaders, HttpStatus.OK);
    }


}
