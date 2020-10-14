package com.charartpav.converte.until;

import com.charartpav.converte.models.UserList;
import com.charartpav.converte.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*@author Artem Charykov*/

@Component
public class UserListValidator implements Validator {
	private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Autowired
	//@Qualifier("UserList_JPA")  - If you will use the DAO layer.
	private UserListService userListService;

	@Override
	public boolean supports(Class<?> type) {
		return UserList.class.equals(type);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserList user = (UserList) obj;
		if(userListService.getByEmail(user.getEmail())!=null){
			errors.rejectValue("email", "", "This Emal is already used.");
		}
		if(userListService.getByUserLogin(user.getUserLogin())!=null){
			errors.rejectValue("UserLogin", "", "This Login is already used.");
		}
		if(user.getEmail().matches(EMAIL_PATTERN)){
			errors.rejectValue("email", "", "In entered an incorrect email address.");
		}
	}	
}