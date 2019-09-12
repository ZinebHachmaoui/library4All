package com.autoformation.libraryForAll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Modele.Livre;

@ManagedBean
@SessionScoped
public class HomePage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7051494855806975895L;
	List<Livre> listLivre = new ArrayList<Livre>();
	
	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	Livre livre = new Livre();

	public String ajouterLivre()
	{
		return "ajouterLivre";
	}
	
	public List<Livre> getListLivre()
	{
		return LivreDAO.getListLivre();
	}

	public void setListLivre(List<Livre> listLivre) {
		this.listLivre = listLivre;
	}
}
