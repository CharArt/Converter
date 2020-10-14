package com.charartpav.converte.service;

import com.charartpav.converte.models.UserList;
import com.charartpav.converte.repository.UserListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*@author Artem Charykov*/

@Service
@Transactional(readOnly = true)
public class UserListServiceImpl implements UserListService{
	
	@Autowired
	UserListRepository userListRepository;
	
	@Override
	public List<UserList> getAll() {
		return userListRepository.findAll();
	}

	@Override
	public List<UserList> getByData (String registrationDate) {
		return userListRepository.findByregistrationDate(registrationDate);
	}

	@Override
	public UserList getByEmail(String Email) {
		return userListRepository.findByEmail(Email);
	}

	@Override
	public UserList getByUserLogin(String UserLogin) {
		return userListRepository.findByUserLogin(UserLogin);
	}

	@Override
	@Transactional
	public void addUser(UserList user) {
		userListRepository.save(user);
	}
}