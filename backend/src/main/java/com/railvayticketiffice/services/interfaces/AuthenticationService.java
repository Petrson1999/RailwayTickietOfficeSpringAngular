package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.data.requests.SignInRequest;
import com.railvayticketiffice.data.responses.JwtResponse;

public interface AuthenticationService {

    JwtResponse signIn(SignInRequest signInRequest);

}
