package com.epsi.epsi_pixel_power_brawl.config;

import java.text.Normalizer.Form;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.epsi.epsi_pixel_power_brawl.service.MyUserDetailsService;
import com.epsi.epsi_pixel_power_brawl.service.UserService;
import com.epsi.epsi_pixel_power_brawl.util.jwt.JwtAuthenticationEntryPoint;
import com.epsi.epsi_pixel_power_brawl.util.jwt.JwtAuthenticationFilter;
import com.epsi.epsi_pixel_power_brawl.util.jwt.JwtAuthorizationFilter;
import com.epsi.epsi_pixel_power_brawl.util.jwt.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private PublicRoutes publicRoutes;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    
    @Autowired
	private JwtUtil jwtUtil;
    	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
    private AuthenticationManager authenticationManager;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
    		.authorizeHttpRequests(auth -> auth
    				.requestMatchers("/admin/**").hasRole("ADMIN")
    				.requestMatchers("/login/**").permitAll()
    				.requestMatchers(publicRoutes.getPublicRoutesAsArray()).permitAll()
    				.anyRequest().authenticated()
    		)
    		.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin().disable();
        return http.build();
    }
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(List.of("http://localhost:4200"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
