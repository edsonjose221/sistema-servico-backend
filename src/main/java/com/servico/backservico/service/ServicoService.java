package com.servico.backservico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.servico.backservico.dto.ServicoDto;
import com.servico.backservico.dto.ServicoForm;
import com.servico.backservico.entity.Servico;
import com.servico.backservico.repository.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	public List<ServicoDto> findAll() {
		List<Servico> servicos = servicoRepository.findAll();
		List<ServicoDto> dtos = convertToList(servicos);
		return dtos;
	}
	
	public ServicoDto findById(Long id) {
		Optional<Servico> servicoId = servicoRepository.findById(id);
		
		if (servicoId.isPresent()) {
			return convertToDto(servicoId.get());
		}
		
		return null;
	}
	
	public List<ServicoDto> findServicesPaymentPending() {
		return servicoRepository.findServicesPaymentPending();
	}
	
	public List<ServicoDto> findServicesCancelled() {
		return servicoRepository.findServicesCancelled();
	}
	
	public ServicoDto save(ServicoForm form) {		
		Servico servico = convertToBusiness(form);
		servicoRepository.save(servico);
		
		if (servico.getValuePaid() == null || servico.getValuePaid() == 0 || servico.getDataPayment() == null) {
			servico.setStatus("pendente");
		} else {
			servico.setStatus("realizado");
		}
		
		ServicoDto dto = convertToDto(servico);
		return dto;
	}
	
	public ServicoDto update(Long id, ServicoForm form) {
		Optional<Servico> servicoId = servicoRepository.findById(id);
		Servico servico = servicoId.get();
		
		if (servicoId.isPresent()) {
			
			servico.setName(form.getName());
			servico.setDataStart(form.getDataStart());
			servico.setDataEnd(form.getDataEnd());
			servico.setDescription(form.getDescription());
			servico.setValueService(form.getValueService());
			servico.setValuePaid(form.getValuePaid());
			servico.setDataPayment(form.getDataPayment());
			servico.setStatus(form.getStatus());
			
			if (servico.getValuePaid() != null && servico.getValuePaid() > 0 && servico.getDataPayment() != null) {
				servico.setStatus("realizado");
			}
			
			servicoRepository.save(servico);
			ServicoDto dto = convertToDto(servico);
			
			return dto;
		}
		
		return null;
	}
	
	public void deleteById(Long id) {
		Optional<Servico> servicoId = servicoRepository.findById(id);

		if (servicoId.isPresent()) {
			servicoRepository.deleteById(id);
		} else {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public void cancelService(Long id) {
		Optional<Servico> servicoId = servicoRepository.findById(id);
		Servico servico = servicoId.get();
		servico.setStatus("cancelado");
		
		servicoRepository.save(servico);
	}
	
	private Servico convertToBusiness(ServicoForm servicoForm) {
		Servico servico = new Servico();
		servico.setName(servicoForm.getName());
		servico.setDataStart(servicoForm.getDataStart());
		servico.setDataEnd(servicoForm.getDataEnd());
		servico.setDescription(servicoForm.getDescription());
		servico.setValueService(servicoForm.getValueService());
		servico.setValuePaid(servicoForm.getValuePaid());
		servico.setDataPayment(servicoForm.getDataPayment());
		
		return servico;
	}
	
	private ServicoDto convertToDto(Servico servico) {
		ServicoDto dto = new ServicoDto();
		dto.setId(servico.getId());
		dto.setName(servico.getName());
		dto.setDataStart(servico.getDataStart());
		dto.setDataEnd(servico.getDataEnd());
		dto.setDescription(servico.getDescription());
		dto.setValueService(servico.getValueService());
		dto.setValuePaid(servico.getValuePaid());
		dto.setDataPayment(servico.getDataPayment());
		dto.setStatus(servico.getStatus());
		
		return dto;
	}
	
	private List<ServicoDto> convertToList(List<Servico> servicos) {		
		return servicos.stream().map(servico -> {
			return new ServicoDto(servico);
		}).collect(Collectors.toList());
	}
}
