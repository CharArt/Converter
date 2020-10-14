package com.charartpav.converte.controllers;

import com.charartpav.converte.service.UserListService;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* @author Artem Charykov*/

@Controller
public class UsersController {

	@Autowired
	//@Qualifier("UserList_JPA") - If you will use the DAO layer.
	private UserListService userListService;

	@GetMapping("/users")
	public String getAllUsers (Model model)throws SQLException {
		model.addAttribute("users", userListService.getAll());
		return"/users";
	}

	@PostMapping
	public String getByData (@RequestParam String RegistrationDate, Model model){
		model.addAttribute("users", userListService.getByData(RegistrationDate));
		return"/users";
	}
}	