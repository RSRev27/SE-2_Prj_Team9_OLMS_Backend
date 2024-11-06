/*
 * package com.se2.proj.olms.security;
 * 
 * import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity;
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * .csrf().disable() // Disable CSRF if needed .authorizeRequests()
 * .antMatchers("/olms/login/**").permitAll() // Allow unauthenticated access to
 * /login endpoint .anyRequest().authenticated() // Protect other endpoints
 * .and() .formLogin().disable(); // Disable the default login page
 * 
 * // Optionally configure other authentication mechanisms like JWT or Basic
 * Authentication } }
 */