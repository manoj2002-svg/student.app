package com.student.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection (for API usage)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/students/**").authenticated()  // Protect /students endpoints
                .anyRequest().permitAll())  // Allow other requests without authentication
            .httpBasic(withDefaults());  // Enable Basic Authentication

        return http.build();
    }

    // In-memory user authentication setup
    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")  // Username: admin
                .password(passwordEncoder().encode("admin123"))  // Password: admin123 (encrypted)
                .roles("USER")  // Role: USER
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // Secure password encoder for BCrypt hashing
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
