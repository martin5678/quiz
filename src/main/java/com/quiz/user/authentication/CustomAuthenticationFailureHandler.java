package com.quiz.user.authentication;


import static com.quiz.common.ApiCommonResult.createErrorCommonResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws
      IOException, ServletException {

    response.setStatus(HttpStatus.OK.value());
    response.setContentType("application/json;charset=UTF-8");
    var result = createErrorCommonResult(exception.getMessage());
    response.getWriter().write(objectMapper.writeValueAsString(result));
  }
}
