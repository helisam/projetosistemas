package br.com.embarcado.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.embarcado.entities.Estado;

public class EstadoRepository {

	private EntityManager entityManager;

	public EstadoRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Estado estado) {
		this.entityManager.persist(estado);
		this.entityManager.flush();
	}

	public void remove(Estado estado) {
		Estado estadoTemp = new Estado();
		estadoTemp = this.entityManager.find(Estado.class, estado.getId());
		this.entityManager.remove(estadoTemp);
	}

	public void update(Estado estado) {
		this.entityManager.merge(estado);
		this.entityManager.flush();
	}

	public Estado find(Long ID) {
		return this.entityManager.find(Estado.class, ID);
	}

	@SuppressWarnings("rawtypes")
	public List getEstados() {
		return this.entityManager.createNamedQuery("Estado.findAll")
				.getResultList();
	}

	public Long getCountEstados() {
		return (Long) this.entityManager.createNamedQuery("Estado.count")
				.getSingleResult();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
