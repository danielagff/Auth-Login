package com.auth.jwt.service.impl;

import com.auth.jwt.model.User;
import com.auth.jwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @Mock
    UserRepository userRepository;


    public void createData()
    {
        User user = new User("User Tester", "teste@teste.com", "123");
        userRepository.save(user);
    }

    @Test
    public void loadByUserName()
    {
        createData();
       String userName = "User Tester";
        UserDetails userDetails = authenticationService.loadUserByUsername(userName);
        int i = 0;
        assertNotNull(userDetails);

    }


}