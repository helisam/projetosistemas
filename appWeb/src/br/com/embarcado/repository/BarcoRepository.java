package br.com.embarcado.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.embarcado.entities.Barco;

public class BarcoRepository {
	private EntityManager entityManager;

	public BarcoRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Barco barco) {
		this.entityManager.persist(barco);
		this.entityManager.flush();
	}

	public void remove(Barco barco) {
		Barco barcoTemp = new Barco();
		barcoTemp = this.entityManager.find(Barco.class, barco.getId());
		this.entityManager.remove(barcoTemp);
	}

	public void update(Barco barco) {
		this.entityManager.merge(barco);
		this.entityManager.flush();
	}

	public Barco find(Long ID) {
		return this.entityManager.find(Barco.class, ID);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("rawtypes")
	public List getBarcos() {
		return this.entityManager.createNamedQuery("Barco.findAll")
				.getResultList();
	}

	public Long getCountBarcos() {
		return (Long) this.entityManager.createNamedQuery("Barco.count")
				.getSingleResult();
	}
}
