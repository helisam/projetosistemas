package br.com.embarcado.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.embarcado.entities.Itinerario;
import br.com.embarcado.repository.BarcoRepository;
import br.com.embarcado.repository.ItinerarioRepository;

@ManagedBean
@SessionScoped
public class ItinerarioBean {

	private Itinerario itinerario = new Itinerario();
	private List<Itinerario> itinerarios = null;
	
	public void save() {
		ItinerarioRepository repository = new ItinerarioRepository(
				this.getManager());
		repository.save(this.itinerario);
		
		this.itinerario = new Itinerario();
		this.itinerarios = null;
	}

	public void setItinerarios(List<Itinerario> itinerarios) {
		this.itinerarios = itinerarios;
	}

	public void update() {
		ItinerarioRepository repository = new ItinerarioRepository(
				this.getManager());
		repository.search(this.itinerario.getId());
		repository.update(this.itinerario);
		
		this.itinerario = new Itinerario();
		this.itinerarios = null;
	}
	
	public void remove(Itinerario itinerario) {
		ItinerarioRepository repository = new ItinerarioRepository(this.getManager());
		repository.remove(itinerario);

		this.itinerario = null;
	}

	@SuppressWarnings("unchecked")
	public List<Itinerario> getItinerarios() {
		if (this.itinerarios == null) {
			ItinerarioRepository repository = new ItinerarioRepository(this.getManager());
			this.itinerarios = repository.getItinerarios();
		}
		return this.itinerarios;
	}

	public Long getCount() {
		BarcoRepository repository = new BarcoRepository(this.getManager());
		return repository.getCountBarcos();
	}

	public String preparaAlterar(Itinerario itinerario) {
		this.setItinerario(itinerario);

		return "index?faces-redirect=true";
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}
}
