package com.tcc.dreams.newdreams.model.service;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.dreams.newdreams.model.Pacote;



//Esta é a definição de uma interface chamada PacoteService, que pode ser usada como um serviço para lidar com operações relacionadas a Pacotes.
@Service
public interface PacoteService {

	// Este método retorna uma lista de todos os Pacotes.
	List<Pacote> findAll();

	// Este método salva um novo Pacote e o retorna.
	Pacote saveNew(Pacote pacote);

	// Este método atualiza as informações de um Pacote existente.
	void update(Pacote pacote);

	// Este método busca um Pacote com base em seu ID (chave primária) e o retorna.
	Pacote findById(int id);

	// Este método permite salvar fotos associadas a um Pacote. Recebe o Pacote e um
	// array de arquivos MultipartFile.
	void salvarFotoPacote(Pacote pacote, MultipartFile[] files);

	// Este método carrega um recurso (geralmente uma foto) com base no nome do
	// arquivo.
	Resource load(String filename);

	boolean verificarDisponibilidadeDoPacote(LocalDate data, String hora, Pacote pacote);
}
