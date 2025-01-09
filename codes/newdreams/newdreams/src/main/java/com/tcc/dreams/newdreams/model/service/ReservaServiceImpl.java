package com.tcc.dreams.newdreams.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.dreams.newdreams.model.Pacote;
import com.tcc.dreams.newdreams.model.Reserva;
import com.tcc.dreams.newdreams.model.User;
import com.tcc.dreams.newdreams.repository.ReservaRepository;



@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final PacoteService pacoteService;

    public ReservaServiceImpl(ReservaRepository reservaRepository, PacoteService pacoteService) {
        this.reservaRepository = reservaRepository;
        this.pacoteService = pacoteService;
    }

    @Override
    public void verificarDisponibilidadeEAtualizar(Reserva reserva) {
        Pacote pacote = reserva.getPacote();
        boolean disponibilidade = verificarDisponibilidade(reserva.getData(), reserva.getHora(), pacote);
        reserva.setDisponivel(disponibilidade);
        reservaRepository.save(reserva);
    }

   

    private boolean verificarDisponibilidade(LocalDate data, String hora, Pacote pacote) {
         //Lógica para verificar se a data, hora e pacote estão disponíveis para reserva
        // Verifica se o pacote está disponível para a data e hora especificadas
        return pacoteService.verificarDisponibilidadeDoPacote(data, hora, pacote);
    }

	@Override
	public List<Reserva> findReservasByUserId(Long idUsuario) {
		
		return reservaRepository.findReservasByUserId(idUsuario);
	}

	@Override
	public List<Reserva> buscarReservasPorUsuario(User user) {
		// TODO Auto-generated method stub
		return null;
	}

   

    // Outros métodos do serviço, se necessário
}
