package mk.ukim.mk.webaud.service.impl;

import mk.ukim.mk.webaud.model.Role;
import mk.ukim.mk.webaud.model.User;
import mk.ukim.mk.webaud.model.exceptions.InvalidArgumentsExceptions;
import mk.ukim.mk.webaud.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.mk.webaud.model.exceptions.PasswordDoNotMatchException;
import mk.ukim.mk.webaud.model.exceptions.UserNameExistsException;
import mk.ukim.mk.webaud.repository.jpa.UserRepository;
import mk.ukim.mk.webaud.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword,
                         String name, String surname, Role role) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(!(password.equals(repeatPassword)))
            throw new PasswordDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UserNameExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()-> new UsernameNotFoundException(s));
    }
}
