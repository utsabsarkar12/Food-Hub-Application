package com.example.food_hub_application.controller;

import com.example.food_hub_application.grobal_data.GrobalData;
import com.example.food_hub_application.model.Role;
import com.example.food_hub_application.model.User;
import com.example.food_hub_application.repository.RoleRepository;
import com.example.food_hub_application.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
//@Bean // Same class must not declared as bean and controller.
@Controller
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(){
        GrobalData.cart.clear();
        return "login";
    }

    @GetMapping("/register")
    public String getResister(){
        return "register";
    }
    @PostMapping("/register")
    public String setRegister(@ModelAttribute("user")User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(),password);
        return "redirect:/";
    }
}
