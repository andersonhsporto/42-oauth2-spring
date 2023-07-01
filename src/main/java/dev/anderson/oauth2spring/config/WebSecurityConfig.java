package dev.anderson.oauth2spring.config;

import dev.anderson.oauth2spring.entities.Role;
import dev.anderson.oauth2spring.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/", "/", "/css/**", "/images/**", "/js/**", "/h2-console/**",
                                        "/login**", "/home**", "/callback/", "/error").permitAll()
                                .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                                .anyRequest().authenticated()

                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                .oauth2Login(
                        oauth2 -> oauth2
                                .defaultSuccessUrl("/welcome", true)
                                .userInfoEndpoint(
                                        userInfo -> userInfo
                                                .userService(customOAuth2UserService)
                                )
                );

        return http.build();
    }
}
