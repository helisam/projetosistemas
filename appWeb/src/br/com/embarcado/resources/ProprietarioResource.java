package br.com.embarcado.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.embarcado.entities.Itinerario;
import br.com.embarcado.resources.util.LocalEntityManagerFactory;

import com.sun.jersey.spi.resource.Singleton;

@Path("/proprietario")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Singleton
public class ProprietarioResource {
	EntityManager em = LocalEntityManagerFactory.createEntityManager();

	@SuppressWarnings("unchecked")
	@GET
	@Path("/lista")
	public List<Itinerario> listagem() {
		List<Itinerario> listas = null;
		try {
			Query query = em.createNamedQuery("Proprietario.findAll");
			listas = query.getResultList();

		} catch (NullPointerException e) {
			System.out.println("Voltou nulo");
			e.printStackTrace();
		}
		return listas;
	}
}
