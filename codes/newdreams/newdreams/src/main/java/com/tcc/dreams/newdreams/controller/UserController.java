package com.tcc.dreams.newdreams.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcc.dreams.newdreams.controller.dto.UserDto;
import com.tcc.dreams.newdreams.model.Pacote;
import com.tcc.dreams.newdreams.model.Role;
import com.tcc.dreams.newdreams.model.User;
import com.tcc.dreams.newdreams.model.service.PacoteService;
import com.tcc.dreams.newdreams.model.service.UserService;
import com.tcc.dreams.newdreams.repository.PacoteRepository;
import com.tcc.dreams.newdreams.repository.RoleRepository;

@Controller
@RequestMapping("/dreamstourism/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;


	@Autowired
	private PacoteRepository Prepository;

	@ModelAttribute("user")
	public UserDto userDto() {
		return new UserDto();
	}



	@GetMapping("/home")
	public String homeUser(Model model) {

		String home = "index";

		User user = userService.getAuthenticatedUser();
		String username = user.getEmail();
		model.addAttribute("username", username);

		return home;

	}
	
	@GetMapping("/dreams/pacote/{id}")
	public String detalhe(@PathVariable("id") long id, Model model) {
		Pacote pacote = Prepository.findById(id).get();
		if (pacote != null) {
			List<String> imagens = new ArrayList<>();
			// Adicione as imagens do pacote à lista imagens
			if (pacote.getFotoCard1() != null) {
				pacote.setFotoCard1String(Base64.getEncoder().encodeToString(pacote.getFotoCard1()));
			}
			if (pacote.getFotoCard2() != null) {
				pacote.setFotoCard2String(Base64.getEncoder().encodeToString(pacote.getFotoCard2()));
			}
			if (pacote.getFotoCard3() != null) {
				pacote.setFotoCard3String(Base64.getEncoder().encodeToString(pacote.getFotoCard3()));
			}
			if (pacote.getFotoCard4() != null) {
				pacote.setFotoCard4String(Base64.getEncoder().encodeToString(pacote.getFotoCard4()));
			}

			model.addAttribute("pacote", pacote);
			return "detalhe-pacote"; // Nome da sua página Thymeleaf
		} else {
			// Lógica para tratar caso o pacote não seja encontrado
			return "pacote-nao-encontrado";
		}
	}
}
