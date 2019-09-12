package com.autoformation.libraryForAll;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Modele.Livre;

public class LivreDAO {

	
	public static List<Livre> getListLivre(){
		List<Livre> listLivre = new ArrayList<Livre>();
		//public Livre(int id, String auteur, String urlFichier, String nom,
		//String resume, String type, LocalDateTime dateEdition,
		//String theme, Blob couverture) {
		listLivre.add(new Livre(1,"Guillaume Musso", "https://www.freeboook.com/2019/04/telecharger-la-vie-secrete-des-ecrivains-en-pdf-gratuit.html",
				"Ghost in Love", " ", "thriller", LocalDateTime.now(), "Love", null));
		listLivre.add(new Livre(2,"Guillaume Musso", "https://www.freeboook.com/2019/04/telecharger-la-vie-secrete-des-ecrivains-en-pdf-gratuit.html",
				"Ghost in Love", " ", "thriller", LocalDateTime.now(), "Love", null));
		listLivre.add(new Livre(3,"Guillaume Musso", "https://www.freeboook.com/2019/04/telecharger-la-vie-secrete-des-ecrivains-en-pdf-gratuit.html",
				"Ghost in Love", " ", "thriller", LocalDateTime.now(), "Love", null));
		listLivre.add(new Livre(4,"Guillaume Musso", "https://www.freeboook.com/2019/04/telecharger-la-vie-secrete-des-ecrivains-en-pdf-gratuit.html",
				"Ghost in Love", " ", "thriller", LocalDateTime.now(), "Love", null));
		listLivre.add(new Livre(5,"Guillaume Musso", "https://www.freeboook.com/2019/04/telecharger-la-vie-secrete-des-ecrivains-en-pdf-gratuit.html",
				"Ghost in Love", " ", "thriller", LocalDateTime.now(), "Love", null));
		listLivre.add(new Livre(6,"Guillaume Musso", "https://www.freeboook.com/2019/04/telecharger-la-vie-secrete-des-ecrivains-en-pdf-gratuit.html",
				"Ghost in Love", " ", "thriller", LocalDateTime.now(), "Love", null));
		return listLivre;	
	}
	
	public static List<Livre> getListLivreParType(String type)
	{
		return null;
	}
	
	public static List<Livre> getListLivreParAuteur(String auteur)
	{
		return null;
	}
	
	public static List<Livre> getListLivreParTheme(String theme)
	{
		return null;
	}
	
	public static void ajouterLivre(Livre livre)
	{
	}
	
	public static void modifierLivre(Livre livre)
	{
	}
	
	public static void supprimerLivre(int id)
	{
	}
}
