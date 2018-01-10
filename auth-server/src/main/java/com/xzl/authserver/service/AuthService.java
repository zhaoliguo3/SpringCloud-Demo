package com.xzl.authserver.service;

import com.xzl.authserver.domain.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
