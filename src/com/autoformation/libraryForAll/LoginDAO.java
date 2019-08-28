package com.autoformation.libraryForAll;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Modele.PasswordResetToken;

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
	public static void reinitialiserPwd(String user) {
		String token = UUID.randomUUID().toString();
		int idUtilisateur = recupererIdUser(user);
		PasswordResetToken myToken = new PasswordResetToken(idUtilisateur,
				token, LocalDateTime.now().plusMinutes(15));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(myToken);
		transaction.commit();

		String url = "http://localhost:8080/LibraryForAll/faces"
				+ "/changePassword?id=" + idUtilisateur + "&token=" + token;
		String subject = "Réinitialisez votre mot de passe";
		String destinataire = "zinebhachmaoui@gmail.com";
		String emmetteur = "zinebhachmaoui@gmail.com";
		String contenu = "Réinitialisez votre mot de passe, veuillez cliquer sur ce lien "
				+ url + "/n Attention ca expire dans 15 minutes.";
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
				.createSQLQuery("select expiryDate from library_for_all.passwordresettoken where id = '"
						+ id + " and token = " + token + ";");
		try {
			Object result = query.getSingleResult();
			if (result != null) {
				LocalDateTime date = (LocalDateTime) result;
				if (date.isAfter(LocalDateTime.now()) || date.equals(LocalDateTime.now())) {
					return "expired";
				}
				else {
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
}
