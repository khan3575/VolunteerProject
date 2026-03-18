package com.sakib.bubt.volunternetwork2.service.interfaces;

import com.sakib.bubt.volunternetwork2.form.RegisterForm;
import com.sakib.bubt.volunternetwork2.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
  User createUser(RegisterForm registrationForm) throws IllegalArgumentException;

  User findByUserName(String username);

  void createDefaultUser();

  List<User> findAllUsers();

  String findUsernameByUserId(String userId);
}
