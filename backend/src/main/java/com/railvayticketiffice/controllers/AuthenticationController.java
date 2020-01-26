package com.railvayticketiffice.controllers;

import com.railvayticketiffice.data.requests.SignInRequest;
import com.railvayticketiffice.data.responses.JwtResponse;
import com.railvayticketiffice.services.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value ="/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<JwtResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

}
