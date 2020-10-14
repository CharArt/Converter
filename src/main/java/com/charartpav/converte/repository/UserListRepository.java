

package com.charartpav.converte.repository;

import com.charartpav.converte.models.UserList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*@author Artem Charykov*/

@Repository
public interface UserListRepository extends JpaRepository<UserList, Long> {

	public List<UserList> findByregistrationDate (String registrationDate);

	public UserList findByEmail(String email);

	public UserList findByUserLogin(String userLogin);
}