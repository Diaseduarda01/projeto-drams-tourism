package com.tcc.dreams.newdreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.dreams.newdreams.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
