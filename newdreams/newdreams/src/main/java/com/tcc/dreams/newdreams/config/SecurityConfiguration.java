package com.tcc.dreams.newdreams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.tcc.dreams.newdreams.model.service.UserService;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
			http.authorizeRequests()
		       .antMatchers("/dreamstourism/registration**", 
				            "/dreamstourism/registration/**",
				            "/dreamstourism/home**",
				            "/js/**", 
				            "/css/**", 
				            "/img/**").permitAll()
				.and().authorizeRequests()	
				.antMatchers(GET, "/dreamstourism/users/**").hasAnyAuthority("ROLE_USER")
				.antMatchers(POST, "/dreamstourism/users/**").hasAnyAuthority("ROLE_USER")
				.antMatchers(GET, "/dreamstourism/admin/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers(POST, "/dreamstourism/admin/**").hasAnyAuthority("ROLE_ADMIN")
			    .anyRequest().authenticated().and()
				.formLogin().loginPage("/dreamstourism/login").permitAll()
				.defaultSuccessUrl("/dreamstourism/living-room", true)
				.passwordParameter("password")
				.usernameParameter("username")
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.deleteCookies("JSESSIONID", "remember-me")
				.invalidateHttpSession(true) // coloquei por conta própria
				.logoutSuccessUrl("/dreamstourism/login?logout")
				.permitAll();

	}
	
	
	

}




