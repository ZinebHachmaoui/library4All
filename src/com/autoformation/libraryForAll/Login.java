package com.autoformation.libraryForAll;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private String pwd;
	private String newpwd;
	private String msg;
	private String user;
	private String id;
	private String nom;
	private String prenom;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	private String confirmerPwd;
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getConfirmerPwd() {
		return confirmerPwd;
	}

	public void setConfirmerPwd(String confirmerPwd) {
		this.confirmerPwd = confirmerPwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	// validate login
	public String connect() {
		boolean valid = LoginDAO.validate(user, pwd);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "homepage";
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"Adresse e-mail/num�ro de t�l�phone portable ou mot de paasse incorrect",
									""));
			return "login";
		}
	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public String goPwdForgottenPage() {
		return "motDePasseOublie";
	}

	public String goLoginPage() {
		return "login";
	}

	public String createAccountPage() {
		return "CreerCompte";
	}

	public String reinitialiserPwd() {
		boolean valid = LoginDAO.validateUser(user);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			LoginDAO.reinitialiserPwd(user);
			return "reinitialiserMdp";
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"Adresse e-mail/numéro de téléphone portable introuvable ou incorrect",
									""));
			return "motDePasseOublie";
		}
	}

	public String modifierPassword() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = (String) params.get("id");
		if (pwd.equals(newpwd)) {
			LoginDAO.modifierPwd(pwd, id);
			return "passwordupdated";
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"Les mots de passe saisis  ne sont pas similaires. Veuillez réessayer",
									""));
			return "updatePassword?faces-redirect=false&id="+id;
		}
	}

	public String createAccount() {
		if (pwd.equals(confirmerPwd)) {
			if (LoginDAO.isAccountExisted(user)) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_WARN,
										"L'adresse e-mail est déjà utilisée. Vous avez indiqué que vous êtes un nouveau client, mais un compte existe déjà avec l'adresse email "
												+ user, ""));
				return "CreerCompte";
			} else {
				String token = LoginDAO.sendCode(nom, prenom, user, pwd);
				return "validateCodeEmail?faces-redirect=true&token=" + token;
			}
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"Mots de passe ne sont pas similaires. Veuillez réessayer",
									""));
			return "CreerCompte";
		}
	}

	public String validateCode() {
		// Recuperer le token
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String token = (String) params.get("token");
		// if true c'est bon on redirige vers la homepage sinon on affiche un
		// message d'erreur
		String email = LoginDAO.validateCode(code, token);
		int idUser = LoginDAO.recupererIdUser(email);
		if (idUser != 0) {
			LoginDAO.validerCompte(idUser,email);
			return "homepage";
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"Code n'est pas correcte. Veuillez réessayer",
									""));
			return "validateCodeEmail?faces-redirect=true&token=" + token;
		}
	}

}
