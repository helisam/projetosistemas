package br.com.embarcado.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.embarcado.entities.Cidade;

public class CidadeRepository {

	private EntityManager entityManager;

	public CidadeRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Cidade cidade) {
		this.entityManager.persist(cidade);
		this.entityManager.flush();
	}

	public void remove(Cidade cidade) {
		Cidade cidadeTemp = new Cidade();
		cidadeTemp = this.entityManager.find(Cidade.class, cidade.getId());
		this.entityManager.remove(cidadeTemp);
	}

	public void update(Cidade cidade) {
		this.entityManager.merge(cidade);
		this.entityManager.flush();
	}

	public Cidade find(Long ID) {
		return this.entityManager.find(Cidade.class, ID);
	}

	@SuppressWarnings("rawtypes")
	public List getCidades() {
		return this.entityManager.createNamedQuery("Cidade.findAll")
				.getResultList();
	}

	public Long getCountCidades() {
		return (Long) this.entityManager.createNamedQuery("Cidade.count")
				.getSingleResult();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
