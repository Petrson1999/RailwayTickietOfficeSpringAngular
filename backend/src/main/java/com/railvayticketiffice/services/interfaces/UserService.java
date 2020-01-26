package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.data.requests.SignUpRequest;
import com.railvayticketiffice.data.responses.UserResponse;

public interface UserService {

    UserResponse createUser(SignUpRequest signUpRequest);

    boolean existsByUsername(String username);

}
