package br.com.embarcado.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.embarcado.entities.Estado;
import br.com.embarcado.repository.BarcoRepository;
import br.com.embarcado.repository.EstadoRepository;

@ManagedBean
@SessionScoped
public class EstadoBean {

	private Estado estado = new Estado();
	private List<Estado> estados = null;

	public void save() {
		EstadoRepository repository = new EstadoRepository(this.getManager());
		repository.save(this.estado);

		this.estado = new Estado();
		this.estados = null;
	}

	public void update() {
		EstadoRepository repository = new EstadoRepository(this.getManager());
		repository.find(this.estado.getId());
		repository.update(this.estado);

		this.estado = new Estado();
		this.estados = null;
	}

	public void remove(Estado estado) {
		EstadoRepository repository = new EstadoRepository(this.getManager());
		repository.remove(estado);

		this.estado = null;
	}

	public Long getCount() {
		BarcoRepository repository = new BarcoRepository(this.getManager());
		return repository.getCountBarcos();
	}

	public String preparaAlterar(Estado estado) {
		this.setEstado(estado);

		return "index?faces-redirect=true";
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@SuppressWarnings("unchecked")
	public List<Estado> getEstados() {
		if (estados == null) {
			EstadoRepository repository = new EstadoRepository(
					this.getManager());
			estados = repository.getEstados();
		}
		return this.estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}

}
