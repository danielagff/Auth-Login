package com.auth.jwt.configuration;

import com.auth.jwt.exception.InvalidTokenException;
import com.auth.jwt.repository.UserRepository;
import com.auth.jwt.service.TokenService;
import com.auth.jwt.service.impl.TokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    @Override
    @ResponseBody
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token;

        var currentToken = request.getHeader("Authorization");

        if(currentToken != null)
        {
            token = currentToken.replace("Bearer ", "");

            var surName = this.tokenService.getUserByToken(token);

            var user = this.userRepository.findBySurName(surName).get();

            var authentication = new UsernamePasswordAuthenticationToken(user
                    ,null
                    , user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    public FilterToken(TokenServiceImpl tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }
}
