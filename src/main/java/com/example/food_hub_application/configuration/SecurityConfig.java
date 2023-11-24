package com.example.food_hub_application.configuration;

import com.example.food_hub_application.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
    @Autowired  //bad practice
    CustomUserDetailService customUserDetailService;

    /** Inject dependency  using constructor
    @Autowired
    SecurityConfig(final CustomUserDetailService customUserDetailService){
        this.customUserDetailService = customUserDetailService;
    }

    */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails normalUser = User.withUsername("user123")
                .password(passwordEncoder().encode("user123"))
                .roles("NORMAL")
                .build();

        UserDetails adminUser = User.withUsername("admin@gmail.com")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests)-> requests.requestMatchers("/**").authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/index")).logout(logout -> logout.permitAll());

        return httpSecurity.build();
    }
}
