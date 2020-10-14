package com.charartpav.converte.security;

import com.charartpav.converte.models.UserList;
import com.charartpav.converte.repository.UserListRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/*@author Artem Charykov*/

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
	@Autowired
	private UserListRepository userListRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		UserList user = userListRepository.findByEmail(email);
		if (user==null){
			throw  new UsernameNotFoundException("User not found");
		}
		String password = authentication.getCredentials().toString();
		if (!password.equals(user.getUserPassword())){
			throw  new BadCredentialsException("Bad credentials");
		}
		List<GrantedAuthority> authority =  new ArrayList<>();
		return new UsernamePasswordAuthenticationToken(user, null, authority);
	}

	@Override
	public boolean supports(Class<?> type) {
		return type.equals(UsernamePasswordAuthenticationToken.class);
	}
}