package com.users.users.config;

import com.users.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpHeaders;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    private final AccessDeniedHandler accessDeniedHandler;


    private final CheckAuthFilter checkAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests) -> {
                            requests
                                    .requestMatchers("/","/save","/singup","/readpost/**","/about", "/fingpost","/contact","/stylesite/**", "/styleauth/**", "/img/**", "/fontawesome/**", "/video/**", "/js/**", "/images/**").permitAll()
                                    .requestMatchers("/login").anonymous()
                                    .anyRequest().authenticated();
                        }
                )
                .exceptionHandling((access)-> access.
                        accessDeniedHandler(accessDeniedHandler)
                )
                .formLogin((form) -> form
                        .loginPage("/login").defaultSuccessUrl("/",true)
                )
                .logout((logout) -> logout.logoutSuccessUrl("/").permitAll())
                .addFilterAfter(checkAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
