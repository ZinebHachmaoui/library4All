package com.autoformation.libraryForAll;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Modele.CreateAccountToken;
import Modele.PasswordResetToken;
import Modele.Utilisateur;

public class LoginDAO {

	public static boolean validate(String email, String password) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session
				.createSQLQuery("select email, password from library_for_all.utilisateur where email = '"
						+ email + "' and password = '" + password + "'");

		try {
			Object result = query.getSingleResult();
			if (result != null) {
				session.close();
				HibernateUtil.shutdown();
				return true;
			}
			session.close();
			HibernateUtil.shutdown();
			return false;
		} catch (NoResultException nre) {
			session.close();
			HibernateUtil.shutdown();
			return false;
		}

	}

	public static boolean validateUser(String emailOrTel) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session
				.createSQLQuery("select email from library_for_all.utilisateur where email = '"
						+ emailOrTel + "'");

		try {
			Object result = query.getSingleResult();
			if (result != null) {
				session.close();
				HibernateUtil.shutdown();
				return true;
			}
			session.close();
			HibernateUtil.shutdown();
			return false;
		} catch (NoResultException nre) {
			session.close();
			HibernateUtil.shutdown();
			return false;
		}

	}

	// Dans cette fonction il faut generer le token et le sauvegarder dans la
	// base
	// Apres Il faut envoyer le mail � l'utilisateur
	public static void reinitialiserPwd(String email) {
		String token = UUID.randomUUID().toString();
		int idUtilisateur = recupererIdUser(email);
		PasswordResetToken myToken = new PasswordResetToken(idUtilisateur,
				token, LocalDateTime.now().plusMinutes(15));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(myToken);
		transaction.commit();

		String url = "http://localhost:8080/LibraryForAll/faces"
				+ "/changePassword?id=" + idUtilisateur + "&token=" + token;
		String subject = "Réinitialisez votre mot de passe";
		String destinataire = email;
		String emmetteur = "account-update@libraryforall.com";
		String contenu = "Réinitialisez votre mot de passe, veuillez cliquer sur ce lien "
				+ url + " Attention ca expire dans 15 minutes.";
		EnvoiMail.envoyerMailSMTP(true, contenu, destinataire, subject,
				emmetteur);
	}

	public static int recupererIdUser(String emailOrTel) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createSQLQuery("select id from library_for_all.utilisateur where email = '"
						+ emailOrTel + "'");
		try {
			Object result = query.getSingleResult();
			if (result != null) {
				session.close();
				HibernateUtil.shutdown();
				return Integer.parseInt(result.toString());
			}
			session.close();
			HibernateUtil.shutdown();
			return 0;
		} catch (NoResultException nre) {
			session.close();
			HibernateUtil.shutdown();
			return 0;
		}
	}

	public static String validatePasswordResetToken(int id, String token) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createSQLQuery("select expiryDate from library_for_all.passwordresettoken where userId = "
						+ id + " and token = '" + token + "';");
		try {
			Timestamp result = (Timestamp) query.getSingleResult();
			if (result != null) {
				LocalDateTime date = result.toLocalDateTime();

				if (date.isBefore(LocalDateTime.now())
						|| date.equals(LocalDateTime.now())) {
					session.close();
					HibernateUtil.shutdown();
					return "expired";
				} else {
					session.close();
					HibernateUtil.shutdown();
					return null;
				}
			}
			session.close();
			HibernateUtil.shutdown();
			return "invalidToken";
		} catch (NoResultException nre) {
			session.close();
			HibernateUtil.shutdown();
			return "exception";
		}
	}

	public static void modifierPwd(String pwd, String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createSQLQuery("update library_for_all.utilisateur set password = '"
						+ pwd + "' where id = '" + id + "';");
		
		Query query2 = session
				.createSQLQuery("delete from library_for_all.passwordresettoken where userId = '" + id + "';");
		try {
			query.executeUpdate();
			query2.executeUpdate();
			session.close();
			HibernateUtil.shutdown();
		} catch (NoResultException nre) {
			session.close();
			HibernateUtil.shutdown();
		}
	}

	public static boolean isAccountExisted(String user) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createSQLQuery("select * from library_for_all.utilisateur where email = '"
						+ user + "' and actif = 1;");
		try {
			Object result = query.getSingleResult();
			if (result != null) {
				session.close();
				HibernateUtil.shutdown();
				return true;
			}
			session.close();
			HibernateUtil.shutdown();
			return false;
		} catch (NoResultException nre) {
			session.close();
			HibernateUtil.shutdown();
			return false;
		}
	}

	public static String sendCode(String nom, String prenom, String email, String pwd) {
		// TODO Auto-generated method stub
		
		String token = UUID.randomUUID().toString();
		int code =  generateCode();
		CreateAccountToken myToken = new CreateAccountToken(email, code, token);
		Utilisateur user = new Utilisateur(nom, prenom, email, pwd, 0);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(myToken);
		session.save(user);
		transaction.commit();
		String subject = "Vérifiez votre nouveau compte LibraryForAll";
		String destinataire = email;
		String emmetteur = "account-create@libraryforall.com";
		String contenu = "Veuillez saisir le code suivant : " + code +" \n Ne partagez ce code avec personne, car cela les aiderait à accéder à votre compte LibraryForAll.";
		EnvoiMail.envoyerMailSMTP(true, contenu, destinataire, subject,
				emmetteur);
		return token;
	}
	
	private static int generateCode()
	{
		return (int) (1000000*Math.random());
	}

	public static String validateCode(int code, String token) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createSQLQuery("select email from library_for_all.createaccounttoken where token = '"
						+ token + "' and code ='" + code + "';");
		try {
			Object result = query.getSingleResult();
			if (result != null) {
				session.close();
				HibernateUtil.shutdown();
				return result.toString();
			}
			session.close();
			HibernateUtil.shutdown();
		} catch (NoResultException nre) {
			session.close();
			HibernateUtil.shutdown();
		}
		return null;
	}

	public static void validerCompte(int idUser, String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createSQLQuery("update library_for_all.utilisateur set actif = "
						+ 1 + " where id = "+ idUser + ";");
		Query query2 = session
				.createSQLQuery("delete from library_for_all.createaccounttoken where email = '"+ email + "';");
		try {
			query.executeUpdate();
			query2.executeUpdate();
			session.close();
			HibernateUtil.shutdown();
		} catch (NoResultException nre) {
			session.close();
			HibernateUtil.shutdown();
		}	
	}
}
