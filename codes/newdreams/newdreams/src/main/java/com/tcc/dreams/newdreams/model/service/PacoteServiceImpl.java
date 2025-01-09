package com.tcc.dreams.newdreams.model.service;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.dreams.newdreams.model.Pacote;
import com.tcc.dreams.newdreams.model.Reserva;



@Service
public class PacoteServiceImpl implements PacoteService {

    @Override
    public List<Pacote> findAll() {
		return null;
        // Implemente a lógica para recuperar todos os pacotes aqui
        // Exemplo: return pacoteRepository.findAll();
    }

    @Override
    public Pacote saveNew(Pacote pacote) {
		return pacote;
        // Implemente a lógica para salvar um novo pacote aqui
    }

    @Override
    public void update(Pacote pacote) {
        // Implemente a lógica para atualizar um pacote aqui
    }

    @Override
    public Pacote findById(int id) {
		return null;
        // Implemente a lógica para encontrar um pacote por ID aqui
    }

	@Override
	public void salvarFotoPacote(Pacote pacote, MultipartFile[] files) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Resource load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarDisponibilidadeDoPacote(LocalDate data, String hora, Pacote pacote) {
	    // Implemente a lógica para verificar a disponibilidade do pacote
	    // Aqui você pode consultar as reservas existentes para o pacote na data e hora específicas
	    // Por exemplo:
	    
	    // 1. Obter a lista de reservas associadas ao pacote
	    List<Reserva> reservas = pacote.getReservas();
	    
	    // 2. Verificar se há conflito com outras reservas para a mesma data e hora
	    for (Reserva reserva : reservas) {
	        // Suponha que a data e a hora da reserva sejam armazenadas em formato String nos atributos data e horaReserva da classe Reserva
	        if (reserva.getData().equals(data) && reserva.getHora().equals(hora)) {
	            return false; // Conflito encontrado, o pacote não está disponível nessa data e hora
	        }
	    }
	    
	    // Se não houver conflito com outras reservas, o pacote está disponível nessa data e hora
	    return true;
	}

}
