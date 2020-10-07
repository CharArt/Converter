package com.charartpav.converte.security;

import com.charartpav.converte.models.UserList;
import com.charartpav.converte.repository.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/* @author Artem Charykov*/

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
	@Autowired
	private UserListRepository userRep;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		UserList user = userRep.findByUserEmail(email);
		return null;
	}

	@Override
	public boolean supports(Class<?> type) {
		return false;
	}

}
