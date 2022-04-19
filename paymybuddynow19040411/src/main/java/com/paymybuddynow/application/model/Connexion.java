package com.paymybuddynow.application.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// classe qui reprend les champs de User et qui inclut un champ de référence à l'user d'appartenance
@Entity
@Table(name = "connexion")
public class Connexion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id" )//c'est l'user auquel est rattaché la connexion
	private Long userId;
	
	@Column(name = "connexion_id")// c'est l'id du connecté
	private Long connexionId;
	
	@Column(name = "connexion_email")
	private String connexionEmail;
	
	@Column(name = "connexion_username")
	private String connexionUsername;
	
	
	public Connexion() {
	}
	
	

	@Override
	public String toString() {
		return "Connexion [id=" + id + ", userId=" + userId + ", connexionId=" + connexionId + ", connexionEmail="
				+ connexionEmail + ", connexionUsername=" + connexionUsername + "]";
	}



	public Connexion(Long userId, Long connexionId, String connexionEmail, String connexionUsername) {
		super();
		
		this.userId = userId;
		this.connexionId = connexionId;
		this.connexionEmail = connexionEmail;
		this.connexionUsername = connexionUsername;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getConnexionId() {
		return connexionId;
	}

	public void setConnexionId(Long connexionId) {
		this.connexionId = connexionId;
	}

	public String getConnexionEmail() {
		return connexionEmail;
	}

	public void setConnexionEmail(String connexionEmail) {
		this.connexionEmail = connexionEmail;
	}

	public String getConnexionUsername() {
		return connexionUsername;
	}

	public void setConnexionUsername(String connexionUsername) {
		this.connexionUsername = connexionUsername;
	}

	
	  @Override public int hashCode() { return Objects.hash(connexionUsername,
	  connexionEmail, connexionId, id, userId); }
	 
	
	  @Override public boolean equals(Object obj) { if (this == obj) return true;
	  if (obj == null) return false; if (getClass() != obj.getClass()) return
	  false; Connexion other = (Connexion) obj; return
	  Objects.equals(connexionUsername, other.connexionUsername) &&
	  Objects.equals(connexionEmail, other.connexionEmail) && connexionId ==
	  other.connexionId && id == other.id && userId == other.userId; }
	 
	
	
}
