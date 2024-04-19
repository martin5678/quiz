package com.quiz.user.service;


import com.quiz.user.model.User;
import com.quiz.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  public User create(User user) {
    return userRepository.save(user);
  }

  public User create(String userName, String password) {
    User u = new User();
    u.setUserName(userName);
    u.setPassword(password);

    return userRepository.save(u);
  }

  public boolean validate(String userName, String password) {
    var user = userRepository.findByUserName(userName);
    if (user.isEmpty()) {
      return false;
    }
    return password.equals(user.get().getPassword());
  }

  public void deleteById(String id) {
    userRepository.deleteById(id);
  }

  public void delete(User user) {
    userRepository.delete(user);
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return userRepository.findByUserName(userName).orElseThrow();
  }

  public User findUserByUserName(String userName) {
    return userRepository.findByUserName(userName).orElseThrow();
  }

}

