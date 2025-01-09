package com.tcc.dreams.newdreams.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tcc.dreams.newdreams.controller.dto.UserDto;
import com.tcc.dreams.newdreams.model.Role;
import com.tcc.dreams.newdreams.model.User;

public interface UserService extends UserDetailsService {

	User save(UserDto userDto);
	User saveUser(User user);
	User findByEmail(UserDto userDto);
	User findById(Long id);
	User update(UserDto userDto);
	User getAuthenticatedUser();
	Role saveRole(Role role);
	List<Role> findAllRoles();
	void addRoleToUser(String username, String roleName);
	List <User> findAllUsersByExcepPrincipalRole (String principalRole);
}
