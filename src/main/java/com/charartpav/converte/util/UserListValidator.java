package com.charartpav.converte.util;

import com.charartpav.converte.dao.UserList_JDBC_API;
import com.charartpav.converte.models.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/* @author Artem Charykov*/
@Component
public class UserListValidator implements Validator {
	
	@Autowired
	private UserList_JDBC_API userList;

	@Override
	public boolean supports(Class<?> type) {
		return UserList.class.equals(type);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserList user = (UserList) obj;
		
	}

}
