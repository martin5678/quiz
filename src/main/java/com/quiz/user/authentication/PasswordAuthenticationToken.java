package com.quiz.user.authentication;


import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class PasswordAuthenticationToken extends AbstractAuthenticationToken {

  private final PasswordAuthenticationPrincipal principal;

  public PasswordAuthenticationToken(PasswordAuthenticationPrincipal principal) {
    super(null);
    this.principal = principal;
    setAuthenticated(false);
  }

  public PasswordAuthenticationToken(PasswordAuthenticationPrincipal principal,
                                Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    super.setAuthenticated(true);
  }

  /**
   * Password auth doesn't need credentials aka password.
   */
  @Override
  public PasswordAuthenticationPrincipal getCredentials() {
    return null;
  }

  @Override
  public PasswordAuthenticationPrincipal getPrincipal() {
    return this.principal;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    if (isAuthenticated) {
      throw new IllegalArgumentException(
          "Cannot set this token to trusted");
    }

    super.setAuthenticated(false);
  }
}

