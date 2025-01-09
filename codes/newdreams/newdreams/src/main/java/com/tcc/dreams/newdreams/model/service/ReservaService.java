package com.tcc.dreams.newdreams.model.service;

import java.util.List;

import com.tcc.dreams.newdreams.model.Reserva;
import com.tcc.dreams.newdreams.model.User;



public interface ReservaService {
	void verificarDisponibilidadeEAtualizar(Reserva reserva);
	 List<Reserva> findReservasByUserId(Long idUsuario);
	 List<Reserva> buscarReservasPorUsuario(User user);

}
