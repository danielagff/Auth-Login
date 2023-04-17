package com.auth.jwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final FilterToken filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

      return http.csrf().disable().sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and().authorizeHttpRequests()
              .requestMatchers("/error").permitAll()
              .requestMatchers(HttpMethod.POST, "/user")
              .permitAll()
              .requestMatchers(HttpMethod.POST, "/user/login")
              .permitAll()
              .anyRequest().authenticated()
              .and()
              .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
              .build();
    }

    public SecurityConfiguration(FilterToken filter) {
        this.filter = filter;
    }


}
