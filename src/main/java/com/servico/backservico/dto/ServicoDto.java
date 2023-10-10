package com.servico.backservico.dto;

import java.util.Date;

import com.servico.backservico.entity.Servico;

public class ServicoDto {
	
	private Long id;
	private String name;
	private Date dataStart;
	private Date dataEnd;
	private String description;
	private Double valueService;
	private Double valuePaid;
	private Date dataPayment;
	private String status;
	
	public ServicoDto() {
	}
	
	public ServicoDto(Servico servico) {
		this.id = servico.getId();
		this.name = servico.getName();
		this.dataStart = servico.getDataStart();
		this.dataEnd = servico.getDataEnd();
		this.description = servico.getDescription();
		this.valueService = servico.getValueService();
		this.valuePaid = servico.getValuePaid();
		this.dataPayment = servico.getDataPayment();
		this.status = servico.getStatus();
		
	}
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
