package com.charartpav.converte.controllers;

import com.charartpav.converte.models.UserList;
import com.charartpav.converte.service.UserListService;
import com.charartpav.converte.until.UserListValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*@author Artem Charykov*/

@Controller
public class RegistrationController {
	
	@Autowired
	private UserListService users;
		
	@Autowired
	private UserListValidator valid;

	@RequestMapping(value = "/registration",method = RequestMethod.GET)
	public String getSingUp(Model model){
		model.addAttribute("user", new UserList ());
		return"/registration";
	}

	@RequestMapping(value = "/registration",method = RequestMethod.POST)
	public String AddUser (@Valid @ModelAttribute("user") UserList user, BindingResult bindingResult, Model model){
		model.addAttribute("user", user);
		valid.validate(user, bindingResult);
		if(bindingResult.hasFieldErrors()){
			/*List<FieldError> err = bindingResult.getFieldErrors();
			for(FieldError e:err){
				System.out.println("Error on object ---> "+e.getObjectName()+" on field ---> "+e.getField()+". Message ---> "+e.getDefaultMessage());
			}*/
			return "/registration";			
		}
		users.addUser(user);
		return "redirect:/users";
	}
}