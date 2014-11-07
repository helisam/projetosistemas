package br.com.embarcado.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.embarcado.entities.Foto;
import br.com.embarcado.utils.HibernateUtil;

public class FotoDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Session session;

	public void save(Foto f) {
		session = HibernateUtil.getSessionfactory().openSession();

		try {
			session.getTransaction().begin();
			session.save(f);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Foto> listByProdutos(long ID) {
		session = HibernateUtil.getSessionfactory().openSession();

		try {
			return session.createCriteria(Foto.class, "f")
					.createAlias("cidade", "c")
					.add(Restrictions.eq("c.id", ID)).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
}
