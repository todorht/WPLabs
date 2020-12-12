package mk.ukim.mk.webaud.service;

import mk.ukim.mk.webaud.model.Role;
import mk.ukim.mk.webaud.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
