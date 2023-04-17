package com.auth.jwt.service;

import com.auth.jwt.dto.Login;
import com.auth.jwt.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    User createUser(User user);
    List<User> getAllUser();
    String login(Login login);

}
