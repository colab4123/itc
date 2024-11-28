package kr.ac.mjc.todolist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()  // 모든 요청에 인증 필요
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)  // 로그인 성공 시 메인 페이지로
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")   // 로그아웃 시 로그인 페이지로
                );

        return http.build();
    }
}