package br.com.embarcado.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.embarcado.entities.Proprietario;

public class ProprietarioRepository {
	private EntityManager entityManager;

	public ProprietarioRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Proprietario prop) {
		this.entityManager.persist(prop);
		this.entityManager.flush();
	}

	public void remove(Proprietario prop) {
		Proprietario propTemp = new Proprietario();
		propTemp = this.entityManager.find(Proprietario.class, prop.getId()); 
		
		this.entityManager.remove(propTemp);
	}
	
	public void update(Proprietario prop){
		this.entityManager.merge(prop);
		this.entityManager.flush();
	}
	
	public Proprietario search(Long ID){
		return this.entityManager.find(Proprietario.class, ID);
	}

	@SuppressWarnings("rawtypes")
	public List getProprietarios() {
		return this.entityManager.createNamedQuery("Proprietario.findAll")
				.getResultList();
	}

	public Long getCountProprietarios() {
		return (Long) this.entityManager.createNamedQuery("Proprietario.count")
				.getSingleResult();
	}

}
