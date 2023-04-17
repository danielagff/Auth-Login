package com.auth.jwt.service.impl;

import com.auth.jwt.dto.Login;
import com.auth.jwt.exception.ExistsException;
import com.auth.jwt.exception.IncorrectInformationException;
import com.auth.jwt.model.User;
import com.auth.jwt.repository.UserRepository;
import com.auth.jwt.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record UserServiceImpl(UserRepository userRepository, TokenServiceImpl token) implements UserService {

    public User createUser(User user) {

        existsByEmail(user.getEmail());

        existsByUserName(user.getUsername());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String login(Login login) {

        authenticateLogin(login);

        User user = userRepository.findByEmail(login.getEmail()).get();

        return token().generateToken(user);
    }
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    private void authenticateLogin(Login login)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userRepository
                .findByEmail(login.getEmail()).orElseThrow(()
                        -> new IncorrectInformationException("User or password are incorrect!"));

        if(!encoder.matches(login.getPassword(), user.getPassword()))
        {
            throw new IncorrectInformationException("User or password are incorrect!");
        }
    }

    private void existsByEmail(String email)
    {
      if(userRepository.existsByEmail(email))
      {
          throw new ExistsException("Exists an User with email!");
      }
    }

    private void existsByUserName(String userName)
    {
        if(userRepository.existsByUserName(userName))
        {
            throw new ExistsException("Exists an User with this user name!");
        }
    }

}
