package com.quiz.user.authentication;


import static com.quiz.user.authentication.PasswordAuthenticationFilter.Constant.REQUEST_PASSWORD_PARAMETER;
import static com.quiz.user.authentication.PasswordAuthenticationFilter.Constant.REQUEST_USER_PARAMETER;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.user.model.LoginRequest;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class PasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  interface Constant {
    String REQUEST_USER_PARAMETER = "userName";
    String REQUEST_PASSWORD_PARAMETER = "password";
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    PasswordAuthenticationToken authRequest;
    if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
      ObjectMapper mapper = new ObjectMapper();
      try (InputStream is = request.getInputStream()) {
        LoginRequest req = mapper.readValue(is, LoginRequest.class);
        PasswordAuthenticationPrincipal principal = getPrincipal(req);
        authRequest = new PasswordAuthenticationToken(principal);
        setDetails(request, authRequest);
      }
    } else {
      PasswordAuthenticationPrincipal principal = getPrincipal(request);
      authRequest = new PasswordAuthenticationToken(principal);

      setDetails(request, authRequest);
    }

    return this.getAuthenticationManager().authenticate(authRequest);
  }

  public PasswordAuthenticationFilter() {
    super(new AntPathRequestMatcher("/User/LoginByUsername", "POST"));
  }

  public PasswordAuthenticationPrincipal getPrincipal(HttpServletRequest request) {
    return new PasswordAuthenticationPrincipal(request.getParameter(REQUEST_USER_PARAMETER),
        request.getParameter(REQUEST_PASSWORD_PARAMETER));
  }

  public PasswordAuthenticationPrincipal getPrincipal(LoginRequest request) {
    return new PasswordAuthenticationPrincipal(request.getUserName(), request.getPassword());
  }

  protected void setDetails(HttpServletRequest request, PasswordAuthenticationToken authRequest) {
    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
  }
}
