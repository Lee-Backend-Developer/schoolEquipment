package com.equipment.school_equipment.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.support.NoOpCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final AuthenticationSuccessHandler successHandler;
    private final InMemoryClientRegistrationRepository inMemoryClientRegistrationRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/admin/**", "/api/admin/**").hasAnyRole("admin")
                                .requestMatchers("/member/account").hasAnyRole("user", "admin")
                                .requestMatchers("/**").permitAll()
                )
                .formLogin(form ->
                        form
                                .loginPage("/member/login")
                                .usernameParameter("id")
                                .passwordParameter("passwd")
                                .successHandler(successHandler)
                )
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutRequestMatcher(AntPathRequestMatcher.antMatcher("/member/logout"))
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true))
                .oauth2Login(httpSecurityOAuth2LoginConfigurer ->
                                httpSecurityOAuth2LoginConfigurer
                                        .defaultSuccessUrl("/"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserCache SpringCacheBasedUserCache() {
        return new SpringCacheBasedUserCache(new NoOpCache("session"));
    }

}
