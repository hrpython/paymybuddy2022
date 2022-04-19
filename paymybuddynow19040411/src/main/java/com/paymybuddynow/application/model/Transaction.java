package com.paymybuddynow.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transmitter_id")
	private Long transmitterId;

	@Column(name = "beneficiary_id")
	private Long beneficiaryId;

	@Column(name = "beneficiary_username")
	private String beneficiaryUsername;

	private double amount;

	private String description;

	public Transaction() {
	}

	public Transaction(Long transmitterId, Long beneficiaryId, String beneficiaryUsername, double amount,
			String description) {
		super();

		this.transmitterId = transmitterId;
		this.beneficiaryId = beneficiaryId;
		this.beneficiaryUsername = beneficiaryUsername;
		this.amount = amount;
		this.description = description;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTransmitterId() {
		return transmitterId;
	}

	public void setTransmitterId(Long transmitterId) {
		this.transmitterId = transmitterId;
	}

	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public String getBeneficiaryUsername() {
		return beneficiaryUsername;
	}

	public void setBeneficiaryUsername(String beneficiaryUsername) {
		this.beneficiaryUsername = beneficiaryUsername;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
