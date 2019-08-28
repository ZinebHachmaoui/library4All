package Modele;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passwordresettoken")
public class PasswordResetToken {

	@Id
	@GeneratedValue
	private int id;
	public PasswordResetToken(int idPasswordResetToken, int idUtilisateur,
			String token, LocalDateTime dateExpiration) {
		super();
		this.id = idPasswordResetToken;
		this.idUtilisateur = idUtilisateur;
		this.token = token;
		this.dateExpiration = dateExpiration;
	}
	private int idUtilisateur;
	private String token;
	public PasswordResetToken(int idUtilisateur, String token,
			LocalDateTime dateExpiration) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.token = token;
		this.dateExpiration = dateExpiration;
	}
	private LocalDateTime dateExpiration;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getToken() {
		return token;
	}
	public LocalDateTime getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(LocalDateTime dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
