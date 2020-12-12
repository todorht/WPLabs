package mk.ukim.mk.webaud.service.impl;

import mk.ukim.mk.webaud.model.User;
import mk.ukim.mk.webaud.model.exceptions.InvalidArgumentsException;
import mk.ukim.mk.webaud.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.mk.webaud.repository.jpa.UserRepository;
import mk.ukim.mk.webaud.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}

