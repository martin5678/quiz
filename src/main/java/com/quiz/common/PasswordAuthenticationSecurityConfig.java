package com.quiz.common;

import com.quiz.user.authentication.CustomAuthenticationFailureHandler;
import com.quiz.user.authentication.CustomAuthenticationSuccessHandler;
import com.quiz.user.authentication.PasswordAuthenticationFilter;
import com.quiz.user.authentication.PasswordAuthenticationProvider;
import com.quiz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
public class PasswordAuthenticationSecurityConfig extends
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Autowired
  private UserService userService;

  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
  @Autowired
  private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    PasswordAuthenticationFilter passwordAuthenticationFilter = new PasswordAuthenticationFilter();
    passwordAuthenticationFilter.setAuthenticationManager(
        http.getSharedObject(AuthenticationManager.class));
    passwordAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
    passwordAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

    PasswordAuthenticationProvider passwordAuthenticationProvider = new PasswordAuthenticationProvider();
    passwordAuthenticationProvider.setUserService(userService);

    http.authenticationProvider(passwordAuthenticationProvider)
        .addFilterBefore(passwordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
