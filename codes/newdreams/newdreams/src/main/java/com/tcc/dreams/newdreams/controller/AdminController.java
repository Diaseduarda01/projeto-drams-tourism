package com.tcc.dreams.newdreams.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcc.dreams.newdreams.controller.dto.UserDto;
import com.tcc.dreams.newdreams.model.Pacote;
import com.tcc.dreams.newdreams.model.Role;
import com.tcc.dreams.newdreams.model.User;
import com.tcc.dreams.newdreams.model.service.PacoteService;
import com.tcc.dreams.newdreams.model.service.UserService;
import com.tcc.dreams.newdreams.repository.PacoteRepository;
import com.tcc.dreams.newdreams.repository.RoleRepository;




@Controller
@RequestMapping("/dreamstourism/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private PacoteService pacoteService;

	@Autowired
	private PacoteRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;

	@GetMapping("/home")
	public String homeAdmin(Model model) {
		String home = "index-admin";
	//	User user = userService.getAuthenticatedUser();
		//String username = user.getEmail();
	//	model.addAttribute("username", userna me);
		return home;
	}

	@GetMapping("/usuarios/todos-usuarios")
	public String showUsuarios(Model model) {
	//	User user = userService.getAuthenticatedUser();
	//	String username = user.getEmail();
		List<User> usuarios = userService.findAllUsersByExcepPrincipalRole("ROLE_ADMIN");
	//	model.addAttribute("username", username);
		model.addAttribute("usuarios", usuarios);

		return "lista-usuarios-admin";
	}

	@GetMapping("/usuarios/editar-user/{id}")
	public String showUpdateFormUser(@PathVariable("id") Long id, Model model) {
		//User user = userService.getAuthenticatedUser();
		List<Role> roles = userService.findAllRoles();
		//String username = user.getEmail();

		User userDb = userService.findById(id);

		model.addAttribute("usuario", userDb);
		model.addAttribute("allRoles", roles);
		//model.addAttribute("username", username);

		return "update-usuario";
	}

	@PostMapping("/usuarios/update-principal-role/{id}")
	public String updatePrincipalRoleUser(@ModelAttribute("user") UserDto userDto, @PathVariable("id") Long id,
			Model model, @RequestParam(value = "roleName", required = false) String roleName) {
		
		User user = userService.getAuthenticatedUser();
		String username = user.getEmail();
		
		User userDb = userService.findById(id);
		Collection<Role> rolesUser = userDb.getRoles();
		
		Role role = roleRepository.findByName(roleName);
		
		// Se o  papel for Papel principal role_user - SÓ PODE SER ROLE_USER
		if(role.getName().equals("ROLE_USER")) {
			rolesUser.removeAll(rolesUser);
			rolesUser.add(role);
		}
		
		if( !rolesUser.contains(role)) {
			rolesUser.add(role);
		}
			
		userDb.setPrincipalRole(roleName);
		
		
		
		userService.saveUser(userDb);
		model.addAttribute("username", username);
		
		return "redirect:/dreamstourism/admin/usuarios/todos-usuarios";
	}

	// Página para listar todos os pacotes existentes.
	@GetMapping("/dreams/pacote/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("lista-pacote");
		modelAndView.addObject("pacotes", repository.findAll());
		return modelAndView;
	}



	// Página para editar um pacote com base no seu ID.
	@GetMapping("/dreams/pacote/{id}/edit")
	public String edit(@PathVariable("id") long id, Model model) {
		Pacote p = repository.findById(id).get();
		if (p == null) {
			return "pacote-nao-encontrado";
		}
		model.addAttribute("pacote", p);
		return "novo-pacote";
	}

	// Remove um pacote com base no seu ID.
	@GetMapping("/dreams/pacote/{id}/delete")
	public String delete(@PathVariable("id") long id) {
		Pacote p = repository.findById(id).get();
		if (p == null) {
			return "pacote-nao-encontrado";
		}
		repository.delete(p); // Remove o pacote do repositório.
		return "redirect:/dreamstourism/admin/dreams/pacote/list";
	}
	
	
	// Página para criar um novo pacote.
	/*@GetMapping("/dreams/pacote/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("novo-pacote");
		modelAndView.addObject("pacote", new Pacote());
		return modelAndView;
	}*/
	
	@GetMapping("/dreams/pacote/novo")
	public String novoPacote(Model model, Pacote pacote) {
		
		model.addAttribute("pacote",pacote );
		return "novo-pacote";
	}
	

	@PostMapping("/dreams/pacote/novo")
	public ModelAndView novo(Pacote pacote, RedirectAttributes redirectAttributes,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2,
			@RequestParam(value = "file3", required = false) MultipartFile file3,
			@RequestParam(value = "file4", required = false) MultipartFile file4) {

		ModelAndView modelAndView = new ModelAndView("redirect:/dreamstourism/admin/dreams/pacote/list");
		String msg = "";

		// Salva a imagem principal (file) no objeto Pacote
		processarArquivo(pacote, file, "foto");

		// Processa e salva as outras imagens (file1, file2, file3, file4)
		processarArquivo(pacote, file1, "foto_card1");
		processarArquivo(pacote, file2, "foto_card2");
		processarArquivo(pacote, file3, "foto_card3");
		processarArquivo(pacote, file4, "foto_card4");

		 if (pacote.getId() == null) {
		        msg = "Parabéns, cadastro feito!";
		    } else {
		        msg = "cadastro atualizado!";
		    }
		 
		redirectAttributes.addFlashAttribute("msg", msg);

		repository.save(pacote); // Salva o pacote no repositório, incluindo as imagens.

		return modelAndView;
	}
	
	// Método para processar e adicionar os arquivos ao pacote.
	private void processarArquivo(Pacote pacote, MultipartFile file, String fieldName) {
		if (file != null && !file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				switch (fieldName) {
				case "foto":
					pacote.setFoto(bytes);
					break;
				case "foto_card1":
					pacote.setFotoCard1(bytes);
					break;
				case "foto_card2":
					pacote.setFotoCard2(bytes);
					break;
				case "foto_card3":
					pacote.setFotoCard3(bytes);
					break;
				case "foto_card4":
					pacote.setFotoCard4(bytes);
					break;
				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	






}
