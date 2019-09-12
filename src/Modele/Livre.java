package Modele;

import java.sql.Blob;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livre")
public class Livre {

	@Id
	@GeneratedValue
	private int id;
	private String auteur;
	private LocalDateTime dateAjout;
	private String urlFichier;
	private String nom;
	private String resume;
	private String type;
	private LocalDateTime dateEdition;
	private String theme;
	private Blob couverture;
	
	public Livre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LocalDateTime getDateEdition() {
		return dateEdition;
	}
	public void setDateEdition(LocalDateTime dateEdition) {
		this.dateEdition = dateEdition;
	}
	public Livre(int id, String auteur, String urlFichier, String nom,
			String resume, String type, LocalDateTime dateEdition,
			String theme, Blob couverture) {
		super();
		this.id = id;
//		this.auteur = auteur;
		this.urlFichier = urlFichier;
		this.nom = nom;
		this.resume = resume;
		this.type = type;
		this.dateEdition = dateEdition;
		this.theme = theme;
		this.couverture = couverture;
	}
	public String getTheme() {
		return theme;
	}
	public Blob getCouverture() {
		return couverture;
	}
	public void setCouverture(Blob couverture) {
		this.couverture = couverture;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public LocalDateTime getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(LocalDateTime dateAjout) {
		this.dateAjout = dateAjout;
	}
	public String getUrlFichier() {
		return urlFichier;
	}
	public void setUrlFichier(String urlFichier) {
		this.urlFichier = urlFichier;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
