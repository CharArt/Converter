package com.charartpav.converte.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* @author Artem Charykov*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		  .antMatchers("/login","/registration").anonymous()
		  .antMatchers("/history","/converter").authenticated()
		  .and().csrf().disable()
		  .formLogin()
		  .loginPage("/login")
		  .loginProcessingUrl("/login/procces")
		  .usernameParameter("email")
		  .and().logout();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth); //To change body of generated methods, choose Tools | Templates.
	}

	@Bean
	public PasswordEncoder encoder(){
			return NoOpPasswordEncoder.getInstance();
	}

}
