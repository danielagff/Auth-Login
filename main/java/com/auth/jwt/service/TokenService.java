package com.auth.jwt.service;

import com.auth.jwt.model.User;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    String generateToken(User user);

    String getUserByToken(String token);

}
