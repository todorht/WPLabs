package mk.ukim.mk.webaud.service;

import mk.ukim.mk.webaud.model.User;

public interface AuthService {
    User login(String username,String password);
}
