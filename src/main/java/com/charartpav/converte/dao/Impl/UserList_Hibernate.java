
package com.charartpav.converte.dao.Impl;

import com.charartpav.converte.dao.UserDAO;
import com.charartpav.converte.models.UserList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/* @author Artem Charykov*/
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
		Query<UserList> q = currentSession().createQuery("from UserList where RegistrationDate := RegistrationDate", UserList.class);
		q.setParameter("RegistrationDate", RegistrationDate);
		return (List<UserList>) q.getResultList().stream().findAny().orElse(null);
	}

	@Override
	public UserList getByEmail(String Email) {
		Query<UserList> q = currentSession().createQuery("from UserList where Email := Email", UserList.class);
		q.setParameter("Email", Email);
		return q.getSingleResult();
	}

	@Override
	public UserList getByUserLogin(String UserLogin) {
		Query<UserList> q = currentSession().createQuery("from UserList where UserLogin := UserLogin", UserList.class);
		q.setParameter("UserLogin", UserLogin);
		return q.getSingleResult();
	}

	@Override
	public void addUser(UserList user) {
		currentSession().save(user);
	}


}
