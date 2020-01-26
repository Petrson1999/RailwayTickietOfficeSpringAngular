package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.data.requests.SignUpRequest;
import com.railvayticketiffice.data.responses.UserResponse;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.exception.RequestException;
import com.railvayticketiffice.repositories.UserRepository;
import com.railvayticketiffice.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(SignUpRequest signUpRequest) {
        LOG.debug("Method was invoked");

        if (existsByUsername(signUpRequest.getLogin())) {
            LOG.error("User with login {} already exists", signUpRequest.getLogin());
            throw new RequestException("Username already exists", HttpStatus.CONFLICT);
        }

        User user = new User(signUpRequest.getLogin(), passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getRole(),
                signUpRequest.getName(), signUpRequest.getSurname());

        return new UserResponse(userRepository.saveAndFlush(user));
    }

    @Override
    public boolean existsByUsername(String login) {
        LOG.debug("Checking if username exists");
        return userRepository.findByLogin(login).isPresent();
    }

}
