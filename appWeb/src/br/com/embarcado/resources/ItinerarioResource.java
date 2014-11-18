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

import br.com.embarcado.entities.Itinerario;
import br.com.embarcado.resources.util.LocalEntityManagerFactory;

import com.sun.jersey.spi.resource.Singleton;

@Path("/itinerario")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Singleton
public class ItinerarioResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String contact;

	EntityManager em = LocalEntityManagerFactory.createEntityManager();

	// private TreeMap<Integer, Itinerario> itinerarioMap = new TreeMap<Integer,
	// Itinerario>();

	/*
	 * public ItinerarioResource() {
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
	public List<Itinerario> listagem() {
		List<Itinerario> listas = null;
		try {
			Query query = em.createNamedQuery("Itinerario.findAll");
			listas = query.getResultList();

		} catch (NullPointerException e) {
			System.out.println("Voltou nulo");
			e.printStackTrace();
		}
		return listas;
	}

	/*
	 * @GET
	 * 
	 * @Path("{id}") public Itinerario getItinerario(@PathParam("id") int id) {
	 * return itinerarioMap.get(id); }
	 * 
	 * @POST
	 * 
	 * @Path("add")
	 * 
	 * @Produces("text/plain")
	 * 
	 * @Consumes(MediaType.APPLICATION_XML) public String
	 * addItinerario(Itinerario itinerario) { int id = itinerarioMap.size();
	 * itinerario.setId((long) id); itinerarioMap.put(id, itinerario); return
	 * "Itinerario \"" + itinerario.getDestino() + "\" added with Id " + id; }
	 */
}
