package com.paymybuddynow.application.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String password;

	private String iban;

	private Boolean isConnexion;

	private Double balance;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//permet de créer une table avec Role pour la sécurité
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)//permet de créer l'ensemble des connexions rattaché à l'user
	@JoinTable(name = "connexion", joinColumns = @JoinColumn(name = "user_id"))
	private List<Connexion> connexions = new ArrayList<>();

	@OneToMany // permet de participer à une transaction en étant emetteur d'argent
	@JoinTable(name = "transaction", joinColumns = @JoinColumn(name = "transmitter_id", columnDefinition = "id"), inverseJoinColumns = @JoinColumn(name = "id", columnDefinition = "id"))
	private List<Transaction> envoiTransaction = new ArrayList<>();

	@OneToMany// permet de participer à une transaction en étant receveur d'argent
	@JoinTable(name = "transaction", joinColumns = @JoinColumn(name = "beneficiary_id", columnDefinition = "id"), inverseJoinColumns = @JoinColumn(name = "id", columnDefinition = "id"))
	private List<Transaction> receptionTransaction = new ArrayList<>();

	public User() {

	}

	public User(String firstName, String lastName, String email, String password, String iban, Boolean isConnexion,
			Double balance, Collection<Role> roles, List<Connexion> connexions, List<Transaction> envoiTransaction,
			List<Transaction> receptionTransaction) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.iban = iban;
		this.isConnexion = isConnexion;
		this.balance = balance;
		this.roles = roles;
		this.connexions = connexions;
		this.envoiTransaction = envoiTransaction;
		this.receptionTransaction = receptionTransaction;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Boolean getisConnexion() {
		return isConnexion;
	}

	public void setisConnexion(Boolean isConnexion) {
		this.isConnexion = isConnexion;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public List<Transaction> getEnvoiTransaction() {
		return envoiTransaction;
	}

	public void setEnvoiTransaction(List<Transaction> envoiTransaction) {
		this.envoiTransaction = envoiTransaction;
	}

	public List<Transaction> getReceptionTransaction() {
		return receptionTransaction;
	}

	public void setReceptionTransaction(List<Transaction> receptionTransaction) {
		this.receptionTransaction = receptionTransaction;
	}

	public List<Connexion> getConnexions() {
		return connexions;
	}

	public void setConnexions(List<Connexion> connexions) {
		this.connexions = connexions;
	}

}
