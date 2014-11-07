package br.com.embarcado.utils;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.embarcado.entities.Cidade;
import br.com.embarcado.entities.Foto;

public class HibernateUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure()
					.addPackage("br.com.embarcado.entities")
					.addAnnotatedClass(Foto.class)
					.addAnnotatedClass(Cidade.class).buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
}