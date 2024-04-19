package com.quiz.common;


import static org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository.DEFAULT_SPRING_SECURITY_CONTEXT_ATTR_NAME;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  public static final String SPRING_SECURITY_CONTEXT = DEFAULT_SPRING_SECURITY_CONTEXT_ATTR_NAME;

  @Autowired
  private PasswordAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.apply(smsAuthenticationSecurityConfig);
    //.and()
    //.authorizeRequests()
    //.antMatchers("/Auth").permitAll()
    //.antMatchers("/comment").permitAll()
    //.antMatchers("/oss").permitAll();

    http
        .logout()
        .invalidateHttpSession(true);
    // Comment out for now
    //.anyRequest().authenticated();
  }

  @Bean
  AuthenticationEntryPoint getAuthenticationEntryPoint() {
    return (request, response, authException) -> {
      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);

      HashMap<String, Object> map = new HashMap<>();
      map.put("status", HttpServletResponse.SC_FORBIDDEN);
      map.put("error", "Forbidden");
      map.put("requiresLogin", true);
      map.put("message", "Not logged in");
      response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    };
  }

  @Bean
  AccessDeniedHandler getAccessDeniedHandler() {
    return (request, response, e) -> {
      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);

      HashMap<String, Object> map = new HashMap<>();
      map.put("status", HttpServletResponse.SC_FORBIDDEN);
      map.put("error", "Forbidden");
      map.put("requiresLogin", false);
      map.put("message", e.getMessage());
      response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    };
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder(7);
  }
}
