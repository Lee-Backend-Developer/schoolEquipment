package com.equipment.school_equipment.config;

import com.equipment.school_equipment.repository.UserRepository;
import com.equipment.school_equipment.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/admin/**", "/api/admin/**").hasAnyRole("관리자")
                                .requestMatchers("/**","/member/login").permitAll()
                )
                .formLogin(form ->
                        form
                                .loginPage("/member/login")
                                .usernameParameter("id")
                                .passwordParameter("passwd")
                                .failureForwardUrl("/member/login")
                                .defaultSuccessUrl("/")
                )
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutRequestMatcher(AntPathRequestMatcher.antMatcher("/member/logout"))
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new LoginUserService();
    }

    @Bean
    public UserRepository userRepository() {
        return new SimpleJpaRepository<>()
    }

    /*@Bean
    public UserDetailsService userDetailsService() { // form 데이터 가져옴
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("aaaa")
                .password("1234")
                .roles("관리자")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }*/
}
