package com.quiz.user.authentication;


import com.quiz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class PasswordAuthenticationProvider implements AuthenticationProvider {


  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    PasswordAuthenticationToken authenticationToken = (PasswordAuthenticationToken) authentication;

    PasswordAuthenticationPrincipal principal = authenticationToken.getPrincipal();
    validatePasswordCode(principal.getUserName(), principal.getPassword());

    var user = userService.loadUserByUsername(principal.getUserName());
    var currentUser = userService.findUserByUserName(principal.getUserName());
    principal.setUserName(user.getUsername());
    principal.setUserId(currentUser.getId());
    PasswordAuthenticationToken authenticationResult =
        new PasswordAuthenticationToken(principal, user.getAuthorities());
    authenticationResult.setDetails(authenticationToken.getDetails());

    return authenticationResult;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return PasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  public void validatePasswordCode(String userName, String password) {

    boolean validated = userService.validate(userName,password);
    if (!validated) {
      throw new BadCredentialsException("Password code and phone doesn't match or code expired");
    }
  }
  public void setUserService(UserService userService) {
    this.userService = userService;
  }


}

