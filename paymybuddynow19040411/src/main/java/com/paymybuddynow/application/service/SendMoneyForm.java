package com.paymybuddynow.application.service;
//classe permettant le traitement de la transaction, cette classe n'est pas une entité c'est une classe utilitaire 
public class SendMoneyForm {

	private Long fromAccountId;
	private Long toAccountId;
	private Double amount;
	private String description;

	public SendMoneyForm() {

	}

	public SendMoneyForm(Long fromAccountId, Long toAccountId, Double amount, String description) {
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public Long getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}