package com.quiz.user;

import static com.quiz.common.ApiCommonResult.createDefaultSuccessCommonResult;
import static com.quiz.common.ApiCommonResult.createSuccessCommonResult;

import com.quiz.common.ApiCommonResult;
import com.quiz.user.model.LoginRequest;
import com.quiz.user.model.RegisterRequest;
import com.quiz.user.model.User;
import com.quiz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {

  @Autowired
  private UserService userService;
  /**
   * Implemented inside spring security.
   */
  @PostMapping("/LoginByUsername")
  public void loginByUserName(
      @RequestBody LoginRequest req) {
  }

  @PostMapping("/Register")
  public ResponseEntity<ApiCommonResult<User>> register(@RequestBody RegisterRequest req) {
    return ResponseEntity.ok(createSuccessCommonResult(userService.create(req.getUserName(), req.getPassword())));
  }

  @PostMapping("/Logout")
  public ResponseEntity<ApiCommonResult<String>> logout() {
    SecurityContextHolder.getContext().setAuthentication(null);

    return ResponseEntity.ok(createDefaultSuccessCommonResult());
  }
}
