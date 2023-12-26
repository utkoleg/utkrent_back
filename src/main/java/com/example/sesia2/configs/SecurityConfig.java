package com.example.sesia2.configs;

import com.example.sesia2.filters.CustomAuthenticationFilter;
import com.example.sesia2.filters.CustomAuthorizationFilter;
import com.example.sesia2.properties.JwtProperties;
import com.example.sesia2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("http://localhost:3000", "http://127.0.0.1:80"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));
            cors.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
                    "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, access_token, refresh_token"));
            return cors;
        }).and();
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.authorizeRequests()
//                .antMatchers("/utkrent/flats/add-flat").hasAuthority("ADMIN")
                .anyRequest().permitAll();
//                .antMatchers("/api/v1/auth/login",
//                        "/api/v1/auth/signup").permitAll()
//                .antMatchers("/api/v1/images").hasAuthority("USER")
//                .antMatchers("/api/v1/auth/add-role-to-user").hasAuthority("ADMIN");

        http.addFilter(jwtAuthorizationFilter());
        http.addFilterBefore(new CustomAuthorizationFilter(jwtProperties), UsernamePasswordAuthenticationFilter.class);
    }

    public CustomAuthenticationFilter jwtAuthorizationFilter() throws Exception {
        CustomAuthenticationFilter jwtAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(), jwtProperties, userRepository);
        jwtAuthenticationFilter.setFilterProcessesUrl("/utkrent/login");
        return jwtAuthenticationFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}