
package com.charartpav.converte.dao.Impl;

import com.charartpav.converte.dao.UserDAO;
import com.charartpav.converte.models.UserList;
import com.charartpav.converte.models.UserRole;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/*@author Artem Charykov*/

@Component
public class UserList_JDBC_Tamplate implements UserDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<UserList> getAll() {
		return jdbcTemplate.query("select * from UserList",
									new BeanPropertyRowMapper<>(UserList.class));
	}

	@Override
	public List<UserList> getByDate (String RegistrationDate){
		return jdbcTemplate.query("select * from UserList where RegistrationDate=?",
									new Object[] {RegistrationDate}, 
									new BeanPropertyRowMapper<>(UserList.class));
	}

	@Override
	public UserList getByEmail (String Email){
		return jdbcTemplate.query("select * from UserList where Email=?",
											new Object[] {Email}, 
											new BeanPropertyRowMapper<>(UserList.class)).stream().findAny().orElse(null);
	}

	@Override
	public UserList getByUserLogin (String UserLogin){
		return jdbcTemplate.query("select * from UserList where UserLogin=?",
											new Object[] {UserLogin}, 
											new BeanPropertyRowMapper<>(UserList.class)).stream().findAny().orElse(null);
	}

	@Override
	public void addUser (UserList user){
		int RoleID = 2; 
		UserRole role = new UserRole();
		role.setUserRoleID(RoleID);
		role.setRole("USER");
		user.setUserRoleID(role);
		jdbcTemplate.update("insert into UserList(UserLogin,UserPassword,Name,Surname,Patronymic,Email,UserRoleID) values(?,?,?,?,?,?,?)",
								user.getUserLogin(),
								user.getUserPassword(),
								user.getName(),
								user.getSurname(),
								user.getPatronymic(),
								user.getEmail(),
								user.getUserRoleID(),
								user.getUserRoleID().getUserRoleID());
	}
}