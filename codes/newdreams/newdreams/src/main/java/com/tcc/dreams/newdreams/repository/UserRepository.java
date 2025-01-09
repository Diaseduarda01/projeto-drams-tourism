package com.tcc.dreams.newdreams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcc.dreams.newdreams.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	@Query(value = "SELECT * FROM users u WHERE u.principal_role <> ?", nativeQuery = true)
	List <User> findAllUsersByExcepPrincipalRole (String principalRole);

}
