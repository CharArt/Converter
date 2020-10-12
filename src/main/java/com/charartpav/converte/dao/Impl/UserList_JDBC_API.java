package com.charartpav.converte.dao.Impl;

import com.charartpav.converte.dao.UserDAO;
import com.charartpav.converte.models.UserList;
import com.charartpav.converte.models.UserRole;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.springframework.stereotype.Component;

/* @author Artem Charykov*/

@Component
public class UserList_JDBC_API implements UserDAO{
	private static Connection conn;
	static{
		String url = null;
		String username = null;
		String password = null;
		try (InputStream input = UserList_JDBC_API.class.getClassLoader().getResourceAsStream("application.properties")) {
			Properties properties = new Properties();
			properties.load(input);
			url=properties.getProperty("spring.datasource.url");
			username=properties.getProperty("spring.datasource.username");
			password=properties.getProperty("spring.datasource.password");
		}
		catch (Exception e) { e.printStackTrace();}
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, username, password);
		} 
		catch (SQLException | ClassNotFoundException ex) { ex.printStackTrace(); }
	}
	
	@Override
	public List<UserList> getAll() {
		try{
			List<UserList> users = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("select * from UserList");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				UserList user = new UserList();
				user.setUserID(resultSet.getLong("UserID"));
				user.setUserLogin(resultSet.getString("UserLogin"));
				user.setUserPassword(resultSet.getString("UserPassword"));
				user.setName(resultSet.getString("Name"));
				user.setSurname(resultSet.getString("Surname"));
				user.setPatronymic(resultSet.getString("Patronymic"));
				user.setEmail(resultSet.getString("Email"));
				user.setRegistrationDate(resultSet.getDate("RegistrationDate"));
				users.add(user);
			}
			return users;
		}
		catch(SQLException ex){ ex.printStackTrace(); }
		return	Collections.EMPTY_LIST;
    }

	@Override
	public List<UserList> getByDate (String RegistrationDate){
		try{
			List<UserList> users = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("select * from UserList where RegistrationDate=?");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				UserList user = new UserList();
				user.setUserID(resultSet.getLong("UserID"));
				user.setUserLogin(resultSet.getString("UserLogin"));
				user.setUserPassword(resultSet.getString("UserPassword"));
				user.setName(resultSet.getString("Name"));
				user.setSurname(resultSet.getString("Surname"));
				user.setPatronymic(resultSet.getString("Patronymic"));
				user.setEmail(resultSet.getString("Email"));
				user.setRegistrationDate(resultSet.getDate("RegistrationDate"));
				users.add(user);
				}
				return users;
			}catch(SQLException ex){ ex.printStackTrace(); }
		return Collections.EMPTY_LIST;
		}
		
	@Override
	public UserList getByEmail (String Email){
		try{
			PreparedStatement ps = conn.prepareStatement("select * from UserList where Email=?");
			ps.setString(1, Email);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				UserList user = new UserList();
				user.setUserID(resultSet.getLong("UserID"));
				user.setUserLogin(resultSet.getString("UserLogin"));
				user.setUserPassword(resultSet.getString("UserPassword"));
				user.setName(resultSet.getString("Name"));
				user.setSurname(resultSet.getString("Surname"));
				user.setPatronymic(resultSet.getString("Patronymic"));
				user.setEmail(resultSet.getString("Email"));
				user.setRegistrationDate(resultSet.getDate("RegistrationDate"));
				return user;
			}
		}catch(SQLException ex){ ex.printStackTrace(); }
		return null;
	}

	@Override
	public UserList getByUserLogin (String UserLogin){
		try{
			PreparedStatement ps = conn.prepareStatement("select * from UserList where UserLogin=?");
			ps.setString(1, UserLogin);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				UserList user = new UserList();
				user.setUserID(resultSet.getLong("UserID"));
				user.setUserLogin(resultSet.getString("UserLogin"));
				user.setUserPassword(resultSet.getString("UserPassword"));
				user.setName(resultSet.getString("Name"));
				user.setSurname(resultSet.getString("Surname"));
				user.setPatronymic(resultSet.getString("Patronymic"));
				user.setEmail(resultSet.getString("Email"));
				user.setRegistrationDate(resultSet.getDate("RegistrationDate"));
				return user;
			}
		}catch(SQLException ex){
			ex.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void addUser (UserList user){
		UserRole role = new UserRole();
		role.setUserRoleID(2);
		role.setRole("USER");
		user.setUserRoleID(role);
		try{
			PreparedStatement ps = conn.prepareStatement(
			  "insert into UserList(UserLogin,UserPassword,Name,Surname,Patronymic,Email,UserRoleID) values(?,?,?,?,?,?,?)");
			ps.setString(1, user.getUserLogin());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSurname());
			ps.setString(5, user.getPatronymic());
			ps.setString(6, user.getEmail());
			ps.setInt(7, user.getUserRoleID().getUserRoleID());
			ps.execute();
		}
		catch(SQLException ex){
			ex.printStackTrace(); 
		}
	}
}
