package com.tcc.dreams.newdreams.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.dreams.newdreams.controller.dto.UserDto;
import com.tcc.dreams.newdreams.model.Role;
import com.tcc.dreams.newdreams.model.User;
import com.tcc.dreams.newdreams.repository.RoleRepository;
import com.tcc.dreams.newdreams.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  
		System.out.println("dddddddddddddsssssssss");
		
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
				passwordEncoder.encode(userDto.getPassword()), new ArrayList<>());

		user.setPrincipalRole("ROLE_USER");
		userRepository.save(user);
		this.addRoleToUser(user.getEmail(), "ROLE_USER");
		return user;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(UserDto userDto) {
		return userRepository.findByEmail(userDto.getEmail());
	}

	@Override
	public User update(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail());
       // Atualizar informações do usuário
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setDataNascimento(userDto.getDataNascimento());

        // Atualizar informações do endereço
		user.setLogradouro(userDto.getLogradouro());
		user.setBairro(userDto.getBairro());
		user.setCep(userDto.getCep());
		user.setCidade(userDto.getCidade());
		user.setUf(userDto.getUf());

		return userRepository.save(user);
	}

	@Override
	public User getAuthenticatedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}else {
			username = principal.toString();
		}
		User user = userRepository.findByEmail(username);
		return user;
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		User user = userRepository.findByEmail(username);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
		userRepository.save(user);

	}

	@Override
	public List<User> findAllUsersByExcepPrincipalRole(String principalRole) {
		return userRepository.findAllUsersByExcepPrincipalRole(principalRole);
	}

	@Override
	public User findById(Long id) {
	
		return userRepository.findById(id).get();
	}

}
