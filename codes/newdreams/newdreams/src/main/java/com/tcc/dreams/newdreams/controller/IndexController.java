package com.tcc.dreams.newdreams.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.dreams.newdreams.model.Pacote;
import com.tcc.dreams.newdreams.repository.PacoteRepository;



@Controller
@RequestMapping("/dreamstourism")
public class IndexController {
	
	@Autowired
	private PacoteRepository repository;
	
	 @GetMapping("/home")
	 public String inicial() {
	    
	     return "inicial";
	 }

	 @GetMapping("/api/pacotes")
		@ResponseBody // Indica que a resposta será em formato JSON
		public List<Pacote> getPacotes() {
			List<Pacote> arrayPacotes = repository.findAll();

			for (Pacote p : arrayPacotes) {
				// Verifica se os campos de imagem não são nulos antes de codificar para Base64
				if (p.getFoto() != null) {
					p.setFotoString(Base64.getEncoder().encodeToString(p.getFoto()));
				}
				if (p.getFotoCard1() != null) {
					p.setFotoCard1String(Base64.getEncoder().encodeToString(p.getFotoCard1()));
				}
				if (p.getFotoCard2() != null) {
					p.setFotoCard2String(Base64.getEncoder().encodeToString(p.getFotoCard2()));
				}
				if (p.getFotoCard3() != null) {
					p.setFotoCard3String(Base64.getEncoder().encodeToString(p.getFotoCard3()));
				}
				if (p.getFotoCard4() != null) {
					p.setFotoCard4String(Base64.getEncoder().encodeToString(p.getFotoCard4()));
				}
			}

			return arrayPacotes; // Retorna a lista de pacotes do seu repositório
		}

}
