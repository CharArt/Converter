
package com.charartpav.converte.dao.Impl;

import com.charartpav.converte.dao.UserDAO;
import com.charartpav.converte.models.UserList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*@author Artem Charykov*/

@Component
public class UserList_Hibernate implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Session currentSession(){
		return sessionFactory.openSession();
	}

	@Override
	public List<UserList> getAll() {
		return currentSession().createQuery("from UserList", UserList.class).list();
	}

	@Override
	public List<UserList> getByDate(String RegistrationDate) {
		Query<UserList> q = currentSession().createQuery("from UserList where registrationDate := registrationDate", UserList.class);
		q.setParameter("registrationDate", RegistrationDate);
		return (List<UserList>) q.getResultList().stream().findAny().orElse(null);
	}

	@Override
	public UserList getByEmail(String email) {
		Query<UserList> q = currentSession().createQuery("from UserList where email := email", UserList.class);
		q.setParameter("email", email);
		return q.getSingleResult();
	}

	@Override
	public UserList getByUserLogin(String UserLogin) {
		Query<UserList> q = currentSession().createQuery("from UserList where userLogin := userLogin", UserList.class);
		q.setParameter("userLogin", UserLogin);
		return q.getSingleResult();
	}

	@Override
	public void addUser(UserList user) {
		currentSession().save(user);
	}
}