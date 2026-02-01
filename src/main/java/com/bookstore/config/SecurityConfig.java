package com.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // For simplicity in this demo, but Requirements say enabled. Wait. User said
                                          // "CSRF enabled".
                // Re-enabling CSRF is tricky with simple forms if not handled locally by
                // Thymeleaf automatically. Thymeleaf handles it.
                // I will enable it properly.
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Just in case, though using MySQL
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/register/**", "/login", "/css/**", "/js/**", "/images/**", "/",
                                "/home", "/book/**", "/books")
                        .permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/cart/**", "/checkout/**", "/order/**", "/profile/**").authenticated()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());
        return http.build();
    }
}
