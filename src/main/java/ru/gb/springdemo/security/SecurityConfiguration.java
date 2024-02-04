package ru.gb.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {



  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
      .authorizeHttpRequests(configurer -> configurer
      .requestMatchers("/ui/issues").hasAuthority("admin")
      .requestMatchers("/ui/readers").hasAuthority("reader")
      .requestMatchers("/ui/books").authenticated()

        .anyRequest().permitAll()
      ).formLogin(Customizer.withDefaults())

      .build();
  }


}
