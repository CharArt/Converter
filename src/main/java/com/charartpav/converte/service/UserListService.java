package com.charartpav.converte.service;

import com.charartpav.converte.models.UserList;
import java.util.List;

/*@author Artem Charykov*/

public interface UserListService {

	public List<UserList> getAll();

	public List<UserList> getByData (String registrationDate);

	public UserList getByEmail(String email);

	public UserList getByUserLogin(String UserLogin);

	public void addUser(UserList user);

}