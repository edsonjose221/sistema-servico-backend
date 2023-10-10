package com.servico.backservico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.servico.backservico.dto.ServicoDto;
import com.servico.backservico.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
	
	@Query("select s from Servico s where s.valuePaid is null or s.valuePaid is null or s.valuePaid = 0")
	List<ServicoDto> findServicesPaymentPending();
	
	@Query("select s from Servico s where s.status = 'cancelado'")
	List<ServicoDto> findServicesCancelled();
}
