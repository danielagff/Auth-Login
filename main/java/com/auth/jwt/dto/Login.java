package com.auth.jwt.dto;

import com.auth.jwt.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Login {


    private String email;

    private String password;

    public Login() {
    }



}
