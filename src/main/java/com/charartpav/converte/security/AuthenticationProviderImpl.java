package com.charartpav.converte.security;

import com.charartpav.converte.repository.UserListRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
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
		User user = userRep.fin
		
	}

	@Override
	public boolean supports(Class<?> type) {
		return false;
	}

}
