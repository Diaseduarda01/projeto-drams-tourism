package com.tcc.dreams.newdreams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.dreams.newdreams.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	@Query(value = "SELECT * FROM reservas r WHERE r.user_id=?", nativeQuery = true)
	List<Reserva> findReservasByUserId(Long idUsuario);

}
