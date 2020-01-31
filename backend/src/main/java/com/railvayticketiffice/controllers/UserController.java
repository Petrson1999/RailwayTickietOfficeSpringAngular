package com.railvayticketiffice.controllers;

import com.railvayticketiffice.data.requests.SignUpRequest;
import com.railvayticketiffice.data.responses.UserResponse;
import com.railvayticketiffice.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<UserResponse> signUpByRegistrationLink(@Valid @RequestBody SignUpRequest signUpRequest) {
        LOG.debug("method was invoked");
        UserResponse userResponse = userService.createUser(signUpRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<UserResponse> getById(@PathVariable int userId) {
        LOG.debug("method was invoked");
        UserResponse userResponse = userService.getById(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


}
