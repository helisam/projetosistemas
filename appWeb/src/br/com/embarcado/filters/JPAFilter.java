package br.com.embarcado.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void destroy() {
		this.entityManagerFactory.close();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		EntityManager entityManager = this.entityManagerFactory
				.createEntityManager();

		// --- Permite que acentos etc sejam inseridos corretamento no banco de
		// dados.
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		req.setAttribute("entityManager", entityManager);

		entityManager.getTransaction().begin();

		fc.doFilter(req, res);

		try {
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new ServletException(e.toString());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		this.entityManagerFactory = Persistence
				.createEntityManagerFactory("embarco");
	}

}
