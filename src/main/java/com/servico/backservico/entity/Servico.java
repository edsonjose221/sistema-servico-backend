package com.servico.backservico.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "servico")
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date dataStart = new Date();
	
	@Temporal(TemporalType.DATE)
	private Date dataEnd;
	
	private String description;
	
	private Double valueService;
	
	private Double valuePaid;
	
	@Temporal(TemporalType.DATE)
	private Date dataPayment;
	
	private String status; // "pendente", "realizado ou "cancelado"

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDataStart() {
		return dataStart;
	}

	public void setDataStart(Date dataStart) {
		this.dataStart = dataStart;
	}

	public Date getDataEnd() {
		return dataEnd;
	}

	public void setDataEnd(Date dataEnd) {
		this.dataEnd = dataEnd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValueService() {
		return valueService;
	}

	public void setValueService(Double valueService) {
		this.valueService = valueService;
	}

	public Double getValuePaid() {
		return valuePaid;
	}

	public void setValuePaid(Double valuePaid) {
		this.valuePaid = valuePaid;
	}

	public Date getDataPayment() {
		return dataPayment;
	}

	public void setDataPayment(Date dataPayment) {
		this.dataPayment = dataPayment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
