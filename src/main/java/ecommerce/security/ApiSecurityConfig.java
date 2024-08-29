package ecommerce.security;

import ecommerce.services.impl.AccessCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class ApiSecurityConfig {

    @Autowired
    private AutenticatorFilter autenticatorFilter;

    private final AccessCredentialsService accessCredentials = new AccessCredentialsService();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accessCredentials);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.applyPermitDefaultValues();
        cors.setAllowedHeaders(Arrays.asList("*"));
        cors.setAllowedMethods(Arrays.asList("*"));
        cors.setAllowedOrigins(Arrays.asList("*"));
        cors.setExposedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource ccs = new UrlBasedCorsConfigurationSource();
        ccs.registerCorsConfiguration("/**", cors);
        return ccs;
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).cors().configurationSource(urlBasedCorsConfigurationSource()).and()
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/auth/**")
                                .permitAll()
                                //Products
                                .requestMatchers(HttpMethod.GET, "/products/**")
                                .hasAuthority("GET PRODUCTS")
                                .requestMatchers(HttpMethod.POST, "/products/**")
                                .hasAuthority("POST PRODUCTS")
                                .requestMatchers(HttpMethod.DELETE, "/products/**")
                                .hasAuthority("DELETE PRODUCTS")
                                .requestMatchers(HttpMethod.PUT, "/products/**")
                                .hasAuthority("PUT PRODUCTS")
                                //Categories
                                .requestMatchers(HttpMethod.GET, "/categories/**")
                                .hasAuthority("GET CATEGORIES")
                                .requestMatchers(HttpMethod.POST, "/categories/**")
                                .hasAuthority("POST CATEGORIES")
                                .requestMatchers(HttpMethod.DELETE, "/categories/**")
                                .hasAuthority("DELETE CATEGORIES")
                                .requestMatchers(HttpMethod.PUT, "/categories/**")
                                .hasAuthority("PUT CATEGORIES")
                                //Roles
                                .requestMatchers(HttpMethod.GET, "/roles/**")
                                .hasAuthority("GET ROLES")
                                .requestMatchers(HttpMethod.POST, "/roles/**")
                                .hasAuthority("POST ROLES")
                                .requestMatchers(HttpMethod.DELETE, "/roles/**")
                                .hasAuthority("DELETE ROLES")
                                .requestMatchers(HttpMethod.PUT, "/roles/**")
                                .hasAuthority("PUT ROLES")
                                //Users
                                .requestMatchers(HttpMethod.GET, "/users/**")
                                .hasAuthority("GET USERS")
                                .requestMatchers(HttpMethod.POST, "/users/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/users/**")
                                .hasAuthority("DELETE USERS")
                                .requestMatchers(HttpMethod.PUT, "/users/**")
                                .hasAuthority("PUT USERS")
                                //Order
                                .requestMatchers(HttpMethod.GET, "/orders/**")
                                .hasAuthority("GET ORDERS")
                                .requestMatchers(HttpMethod.POST, "/orders/**")
                                .hasAuthority("POST ORDERS")
                                .requestMatchers(HttpMethod.DELETE, "/orders/**")
                                .hasAuthority("DELETE ORDERS")
                                .requestMatchers(HttpMethod.PUT, "/orders/**")
                                .hasAuthority("PUT ORDERS")


                                .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(autenticatorFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(c -> urlBasedCorsConfigurationSource());
        return http.build();
    }

}