package com.charartpav.converte.dao.Impl;

import com.charartpav.converte.dao.UserDAO;
import com.charartpav.converte.models.UserList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*@author Artem Charykov*/

@Component
@Transactional(readOnly = true)
public class UserList_JPA implements UserDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<UserList> getAll() {
		return entityManager.createQuery("select ul from UserList ul", UserList.class).getResultList();
	}

	@Override
	public List<UserList> getByDate(String registrationDate) {
		TypedQuery<UserList> typedQuery = entityManager.createQuery("select ul from UserList ul where ul.RegistrationDate := RegistrationDate",UserList.class);
		typedQuery.setParameter("RegistrationDate", registrationDate);
		return (List<UserList>) typedQuery.getResultList().stream().findAny().orElse(null);
	}

	@Override
	public UserList getByEmail(String Email) {
		TypedQuery<UserList> typedQuery = entityManager.createNamedQuery("UserList.findByEmail", UserList.class);
		typedQuery.setParameter("email", Email);
		return typedQuery.getSingleResult();
	}

	@Override
	public UserList getByUserLogin(String UserLogin) {
		TypedQuery<UserList> typedQuery = entityManager.createNamedQuery("UserList.findByUserLogin", UserList.class);
		typedQuery.setParameter("UserLogin", UserLogin);
		return typedQuery.getSingleResult();
	}

	@Transactional
	@Override
	public void addUser(UserList user) {
		entityManager.persist(user);
	}
}
//аврвар