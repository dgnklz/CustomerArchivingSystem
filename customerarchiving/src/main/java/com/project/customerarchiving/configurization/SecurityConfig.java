package com.project.customerarchiving.configurization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.project.customerarchiving.business.concretes.UserDetailsServiceImplementation;
import com.project.customerarchiving.security.JwtAuthenticationEntryPoint;
import com.project.customerarchiving.security.JwtAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserDetailsServiceImplementation userDetailsService;
	private JwtAuthenticationEntryPoint handler;
	
	public SecurityConfig(UserDetailsServiceImplementation userDetailsService, JwtAuthenticationEntryPoint handler) {
		this.userDetailsService = userDetailsService;
		this.handler = handler;
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	//it's a filter to respond to requests from servers origins other than our origin
	@Bean
	public CorsFilter corsFilter() {
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.cors()
			.and()
			
			//cross site request forgery
			//It blocks fake requests that use a logged-in user's session to perform operations except for the user's request.
			.csrf().disable()
			
			.exceptionHandling().authenticationEntryPoint(handler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			
			//Anyone can enter the defined URL "/auth". but all requests must be authenticated
			.antMatchers("/auth/**")
			.permitAll()
			.anyRequest().authenticated();
		
		//???
		httpSecurity.addFilterBefore(jwtAuthenticationFilter(), null);
	}
	
}
