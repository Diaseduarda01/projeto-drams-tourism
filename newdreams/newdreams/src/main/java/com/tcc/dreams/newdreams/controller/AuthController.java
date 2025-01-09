package com.tcc.dreams.newdreams.controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.dreams.newdreams.controller.dto.UserDto;
import com.tcc.dreams.newdreams.model.Role;
import com.tcc.dreams.newdreams.model.User;
import com.tcc.dreams.newdreams.model.service.UserService;

@Controller
public class AuthController {

	

	private UserService userService;
	
	public AuthController(UserService userService) {
		
		this.userService = userService;
	}
	
	
	@ModelAttribute("user")
	public UserDto userDto() {
		return new UserDto();
	}
	
	@GetMapping("/dreamstourism/registration")
	public String showRegistrationForm() {
		return "registration";
	}
	
	
	@PostMapping("/dreamstourism/registration")
	public String registerUserAccount(@ModelAttribute("user") UserDto userDto) {
		userService.save(userDto);
		return "redirect:/registration?success";
	}
	
	@ResponseBody
	@RequestMapping(value="/dreamstourism/registration/ajax/getEmail/{campo}/{valor}")
	public String getSearchResultViaAjaxRegister(@PathVariable("campo") String campo,
			                                     @PathVariable("valor") String valor) {
		
		String msg= "";
		UserDto userDto = new UserDto();
		userDto.setEmail(valor);
		User user = userService.findByEmail(userDto);
		if(user != null) {
			msg = "Email já existe, escolha um email válido!";
		}
		return msg;
	}
	
	@GetMapping("/dreamstourism/login")
	public String login() {
		return "login";
	}

	@GetMapping("/dreamstourism/living-room")
	public String livingRoom() {

		String home = "redirect:/dreamstourism/login";
		User user = userService.getAuthenticatedUser();
		String principalRole = user.getPrincipalRole();
		Collection<Role> roles = user.getRoles();

		for (Role r : roles) {
			System.out.println(r.getName());
			if (r.getName().equals("ROLE_ADMIN") && principalRole.equals("ROLE_ADMIN")) {
				home = "redirect:/dreamstourism/admin/home";
			
			} else if (r.getName().equals("ROLE_USER") && principalRole.equals("ROLE_USER")) {
				home = "redirect:/dreamstourism/users/home";
			}
		}

		return home;

	}
}
