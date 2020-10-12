package com.charartpav.converte.repository;

import com.charartpav.converte.models.UserList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*@author Artem Charykov*/

@Repository
public interface UserListRepository extends CrudRepository<UserList, Long> {

public UserList findByEmail(String email);

}
