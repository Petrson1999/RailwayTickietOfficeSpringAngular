package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.data.requests.SignInRequest;
import com.railvayticketiffice.data.responses.JwtResponse;
import com.railvayticketiffice.security.JwtTokenProvider;
import com.railvayticketiffice.security.user.UserPrincipal;
import com.railvayticketiffice.services.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtProvider;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public JwtResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getLogin(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String accessToken = jwtProvider.generateAccessToken(userPrincipal.getUsername(), userPrincipal.getRole());

        return new JwtResponse(accessToken,
                "Bearer",
                userPrincipal.getId(),
                userPrincipal.getAuthorities());
    }

}
