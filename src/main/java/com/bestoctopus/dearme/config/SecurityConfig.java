package com.bestoctopus.dearme.config;

import com.bestoctopus.dearme.token.JwtAuthenticationProvider;
import com.bestoctopus.dearme.token.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public SecurityConfig(
            AuthenticationManagerBuilder authenticationManagerBuilder,
            JwtAuthenticationProvider jwtAuthenticationProvider
    ) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers("/user/**").permitAll()
                                .anyRequest().hasAnyRole(Role.ADMIN.getRole(), Role.NORMAL.getRole())
                )
                .cors(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .apply(new JwtSecurityConfig(authenticationManagerBuilder.getOrBuild()));

        return http.build();
    }
}
