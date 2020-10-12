package com.charartpav.converte.until;

import com.charartpav.converte.dao.UserDAO;
import com.charartpav.converte.models.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/* @author Artem Charykov*/
@Component
public class UserListValidator implements Validator {
	private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Autowired
	@Qualifier("UserList_Hibernate")
	private UserDAO userDAO;

	@Override
	public boolean supports(Class<?> type) {
		return UserList.class.equals(type);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserList user = (UserList) obj;
		if(userDAO.getByEmail(user.getEmail())!=null){
			errors.rejectValue("Email", "", "This Emal is already used.");
		}
		
		if(userDAO.getByUserLogin(user.getUserLogin())!=null){
			errors.rejectValue("userLogin", "", "This Login is already used.");
		}
		if(user.getEmail().matches(EMAIL_PATTERN)){
			errors.rejectValue("userLogin", "", "In entered an incorrect email address.");
		}
	}	
}