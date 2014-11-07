package br.com.embarcado.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.embarcado.entities.Cidade;
import br.com.embarcado.utils.HibernateUtil;

public class CidadeDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Session session;

	public void save(Cidade c) {
		session = HibernateUtil.getSessionfactory().openSession();

		try {
			session.getTransaction().begin();
			session.save(c);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> listAll() {
		session.getSessionFactory().openSession();

		try {
			return session.createCriteria(Cidade.class, "c").list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
