package br.com.embarcado.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.embarcado.entities.Itinerario;

public class ItinerarioRepository {
	private EntityManager entityManager;
	
	public ItinerarioRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Itinerario itinerario) {
		this.entityManager.persist(itinerario);
		this.entityManager.flush();
	}

	public void remove(Itinerario itinerario) {
		Itinerario itinerarioTemp = new Itinerario();
		itinerarioTemp = this.entityManager.find(Itinerario.class, itinerario.getId());
		this.entityManager.remove(itinerarioTemp);
	}
	
	public void update(Itinerario itinerario){
		this.entityManager.merge(itinerario);
		this.entityManager.flush();
	}
	
	public Itinerario find(Long ID) {
		return this.entityManager.find(Itinerario.class, ID);
	}
	
	public Itinerario search(Long ID){
		return this.entityManager.find(Itinerario.class, ID);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("rawtypes")
	public List getItinerarios() {
		return this.entityManager.createNamedQuery("Itinerario.findAll")
				.getResultList();
	}

	public Long getCountItinerarios() {
		return (Long) this.entityManager.createNamedQuery("Itinerario.count")
				.getSingleResult();
	}
}
