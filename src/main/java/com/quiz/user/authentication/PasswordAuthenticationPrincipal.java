package com.quiz.user.authentication;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordAuthenticationPrincipal implements Serializable {
  String userName;
  String password;
  String userId;


  public PasswordAuthenticationPrincipal(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }
}
