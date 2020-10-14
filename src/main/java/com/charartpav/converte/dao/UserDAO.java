
package com.charartpav.converte.dao;

import com.charartpav.converte.models.UserList;
import java.util.List;

/*@author Artem Charykov*/

public interface UserDAO {
	
	public List<UserList> getAll();

	public List<UserList> getByDate(String RegistrationDate);

	public UserList getByEmail(String Email);

	public UserList getByUserLogin(String UserLogin);

	public void addUser (UserList user);
}