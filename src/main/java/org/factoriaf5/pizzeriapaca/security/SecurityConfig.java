package org.factoriaf5.pizzeriapaca.security;

import java.util.Arrays;

import java.util.Base64;
import org.springframework.boot.CommandLineRunner;


import org.factoriaf5.pizzeriapaca.uploadimage.local.services.implementations.IStorageService;
import org.factoriaf5.pizzeriapaca.users.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.time.Duration;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${api-endpoint}")
    private String endpoint;

    private final JpaUserDetailsService jpaUserDetailsService;
    private final MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService, MyBasicAuthenticationEntryPoint basicEntryPoint) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.myBasicAuthenticationEntryPoint = basicEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfiguration()))
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .logout(out -> out
                .logoutUrl(endpoint + "/logout")
                .deleteCookies("JSESSIONID"))
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()

                .requestMatchers(HttpMethod.GET, endpoint + "/login").hasAnyRole("USER", "ADMIN", "KITCHEN", "MOTORIST")
                .requestMatchers(HttpMethod.POST, endpoint + "/register").permitAll()
                .requestMatchers(HttpMethod.GET, endpoint + "/users/{id}").hasAnyRole("USER", "ADMIN", "KITCHEN", "MOTORIST")
                .requestMatchers(HttpMethod.DELETE, endpoint + "/**").hasAnyRole( "ADMIN")
                .requestMatchers(HttpMethod.POST, endpoint + "/**").hasAnyRole( "ADMIN")
                .requestMatchers(HttpMethod.PUT, endpoint + "/**").hasAnyRole( "ADMIN","KITCHEN", "MOTORIST")
                .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailsService)
                .httpBasic(basic -> basic.authenticationEntryPoint(myBasicAuthenticationEntryPoint))
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        http.headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("", "Content-Type"));
        configuration.setMaxAge(Duration.ofHours(1)); 
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    Base64.Encoder base64Encoder() {
        return Base64.getEncoder();
    }

    @Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
    }
}
