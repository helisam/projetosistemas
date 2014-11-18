package br.com.embarcado.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import br.com.embarcado.entities.Estado;
import br.com.embarcado.resources.util.LocalEntityManagerFactory;

import com.sun.jersey.spi.resource.Singleton;

@Path("/estado")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Singleton
public class EstadoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String contact;

	EntityManager em = LocalEntityManagerFactory.createEntityManager();

	// private TreeMap<Integer, Estado> itinerarioMap = new TreeMap<Integer,
	// Estado>();

	/*
	 * public EstadoResource() {
	 * 
	 * // hardcode a single book information into the database for
	 * 
	 * // demonstration purposes
	 * 
	 * Itinerario itinerario = new Itinerario(); Cidade cidade = new Cidade();
	 * cidade.setNome("Iranduba"); itinerario.setDestino(cidade);
	 * itinerario.setData("12/03/2014"); itinerario.setDistancia("200");
	 * itinerario.setHoraChegada("12:00"); itinerario.setHoraSaida("13:00");
	 * itinerario.setLocalPartida("Teste");
	 * itinerario.setObservacao("usadhiausdhiasudhsaiudhsauidhsaidu");
	 * addItinerario(itinerario); }
	 */

	@SuppressWarnings("unchecked")
	@GET
	@Path("/lista")
	public List<Estado> listagem() {
		List<Estado> listas = null;
		try {
			Query query = em.createNamedQuery("Estado.findAll");
			listas = query.getResultList();

		} catch (NullPointerException e) {
			System.out.println("Voltou nulo");
			e.printStackTrace();
		}
		return listas;
	}

}
