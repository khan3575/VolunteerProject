package com.sakib.bubt.volunternetwork2.controller;

import com.sakib.bubt.volunternetwork2.form.RegisterForm;
import com.sakib.bubt.volunternetwork2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@RequestMapping("/register")
public class RegistrationController {

  private final UserService userService;

  @Autowired
  public RegistrationController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping
  public String Register(Model model) {
    model.addAttribute("registerForm", new RegisterForm());
    return "registration";
  }
  @PostMapping
  public String postRegistrationForm(@Valid RegisterForm registrationForm, BindingResult result) {

    if (result.hasErrors()) {
      log.info("User registration contained errors: " + registrationForm.toString());
      return "registration";
    }
    if (userService.findByUserName(registrationForm.getEmail()) != null) {
      String objectName = "registrationForm";
      String field = "email";
      String message = "An account already exists for this email.";
      FieldError error = new FieldError(objectName, field, message);
      result.addError(error);
      return "registration";
    }

    try {
      userService.createUser(registrationForm);
    } catch (Exception e) {
      log.error(e.getLocalizedMessage());
      return "registration";
    }

    return "redirect:/login";
  }
}
