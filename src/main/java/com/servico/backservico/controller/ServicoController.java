package com.servico.backservico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servico.backservico.dto.ServicoDto;
import com.servico.backservico.dto.ServicoForm;
import com.servico.backservico.service.ServicoService;

@RestController
@RequestMapping("/api/service")
public class ServicoController {
	
	@Autowired
	ServicoService servicoService;
	
	@GetMapping
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<List<ServicoDto>> listAll() {
		List<ServicoDto> dtos = servicoService.findAll();
		return new ResponseEntity<List<ServicoDto>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<ServicoDto> findById(@PathVariable("id") Long id) {
		ServicoDto dto = servicoService.findById(id);
		return new ResponseEntity<ServicoDto>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/paymentPending")
	@CrossOrigin("http://localhost:3000")
	public List<ServicoDto> findServicesPaymentPending() {
		return servicoService.findServicesPaymentPending();
	}
	
	@GetMapping("/cancelleds")
	@CrossOrigin("http://localhost:3000")
	public List<ServicoDto> findServicesCancelleds() {
		return servicoService.findServicesCancelled();
	}
	
	@PostMapping("/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> cancelService(@PathVariable("id") Long id) {
		servicoService.cancelService(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<ServicoDto> save(@RequestBody ServicoForm form) {
		try {
			return new ResponseEntity<ServicoDto>(servicoService.save(form), HttpStatus.CREATED);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<ServicoDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<ServicoDto> update(@PathVariable("id") Long id, @RequestBody ServicoForm form) {
		try {
			return new ResponseEntity<ServicoDto>(servicoService.update(id, form), HttpStatus.CREATED);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<ServicoDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin("http://localhost:3000")
	public void deleteById(@PathVariable("id") Long id) {
		servicoService.deleteById(id);
	}
}
