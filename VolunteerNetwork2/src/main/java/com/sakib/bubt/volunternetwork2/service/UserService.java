package com.sakib.bubt.volunternetwork2.service;

import com.sakib.bubt.volunternetwork2.form.RegisterForm;
import com.sakib.bubt.volunternetwork2.mapper.UserModelMapper;
import com.sakib.bubt.volunternetwork2.model.Address;
import com.sakib.bubt.volunternetwork2.model.User;
import com.sakib.bubt.volunternetwork2.repository.AddressRepository;
import com.sakib.bubt.volunternetwork2.repository.UserRepository;
import com.sakib.bubt.volunternetwork2.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AddressRepository addressRepository;

  private final Environment environment;

  @Autowired
  public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, AddressRepository addressRepository, Environment environment) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.addressRepository = addressRepository;
    this.environment = environment;
  }


  @Override
  public User createUser(RegisterForm registrationForm) throws IllegalArgumentException {
    try {
      User user = UserModelMapper.fromRegistrationForm(registrationForm, passwordEncoder);
      Address address = user.getAddress();
      addressRepository.save(address);
      return userRepository.save(user);
    } catch (IllegalArgumentException exception) {
      log.error("Exception occurred" + exception.getLocalizedMessage());
      throw exception;
    }
  }

  @Override
  public User findByUserName(String username) {
    return this.userRepository.findUserByUsername(username);
  }

  @Override
  public void createDefaultUser() {
    if (!this.userRepository.findAll().isEmpty()) {
      return;
    }

    User user = UserModelMapper.fromEnvironment(environment, passwordEncoder);
    addressRepository.save(user.getAddress());
    userRepository.save(user);
    log.info("createDefaultUser executed");
  }

  @Override
  public List<User> findAllUsers() {
    return this.userRepository.findAll();
  }

  @Override
  public String findUsernameByUserId(String userId) {
    User user = this.userRepository.findById(Long.parseLong(userId)).orElse(null);

    return user != null && !user.getUsername().isBlank() ? user.getUsername() : "";

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findUserByUsername(username);
  }

}