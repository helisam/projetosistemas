package br.com.embarcado.repository;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.embarcado.entities.Foto;

@ManagedBean
@SessionScoped
public class FotoRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public FotoRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Foto foto) {
		this.entityManager.persist(foto);
		this.entityManager.flush();
	}

	public void remove(Foto foto) {
		Foto fotoTemp = new Foto();
		fotoTemp = this.entityManager.find(Foto.class, foto.getId());
		this.entityManager.remove(fotoTemp);
	}

	public void update(Foto foto) {
		this.entityManager.merge(foto);
		this.entityManager.flush();
	}

	public Foto find(Long ID) {
		return this.entityManager.find(Foto.class, ID);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("rawtypes")
	public List getFotos() {
		return this.entityManager.createNamedQuery("Foto.findAll")
				.getResultList();
	}

	public Long getCountFotos() {
		return (Long) this.entityManager.createNamedQuery("Foto.count")
				.getSingleResult();
	}
}
