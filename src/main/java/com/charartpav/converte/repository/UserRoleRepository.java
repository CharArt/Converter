package com.charartpav.converte.repository;

import com.charartpav.converte.models.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*@author Artem Charykov*/

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
