package com.autoformation.libraryForAll;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	  private static SessionFactory sessionFactory;

	  public static SessionFactory getSessionFactory() {
		     if (sessionFactory == null) {
		            // loads configuration and mappings
		            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		            configuration.addClass(Modele.Utilisateur.class);
		            configuration.addClass(Modele.PasswordResetToken.class);
		            configuration.addClass(Modele.CreateAccountToken.class);
		            ServiceRegistry serviceRegistry
		                = new StandardServiceRegistryBuilder()
		                    .applySettings(configuration.getProperties()).build();
		             
		            // builds a session factory from the service registry
		            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
		        }
		         
		        return sessionFactory;
	  }

	  public static void shutdown() {
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }
	}
