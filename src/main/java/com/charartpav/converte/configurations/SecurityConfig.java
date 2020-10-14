package com.charartpav.converte.configurations;

import com.charartpav.converte.security.AuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*@author Artem Charykov*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationProviderImpl providerImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login","/registration").anonymous()
								.antMatchers("/history","/converter").authenticated()
								.antMatchers("/users").hasAnyRole("ADMIN")
								.and().csrf().disable()
								.formLogin()
								.loginPage("/login")
								.loginProcessingUrl("/login/Authentication")
								.usernameParameter("email")
								.and().logout();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(providerImpl);
	}
}