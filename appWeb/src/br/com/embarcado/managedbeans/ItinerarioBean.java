package br.com.embarcado.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.embarcado.entities.Cidade;
import br.com.embarcado.entities.Itinerario;
import br.com.embarcado.repository.BarcoRepository;
import br.com.embarcado.repository.CidadeRepository;
import br.com.embarcado.repository.ItinerarioRepository;

//@Path("/itinerario")
@ManagedBean
@SessionScoped
public class ItinerarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Itinerario itinerarioSelecionado;
	private Itinerario itinerario = new Itinerario();
	private List<Itinerario> itinerarios = null;
	private Long cidadeID;

	public void save() {
		CidadeRepository cityRepository = new CidadeRepository(
				this.getManager());
		Cidade destino = cityRepository.find(cidadeID);
		this.itinerario.setDestino(destino);

		ItinerarioRepository repository = new ItinerarioRepository(
				this.getManager());
		repository.save(this.itinerario);

		this.itinerario = new Itinerario();
		this.itinerarios = null;
	}

	public void update() {
		CidadeRepository cityRepository = new CidadeRepository(
				this.getManager());
		Cidade destino = cityRepository.find(cidadeID);
		this.itinerario.setDestino(destino);

		ItinerarioRepository repository = new ItinerarioRepository(
				this.getManager());
		repository.search(this.itinerario.getId());
		repository.update(this.itinerario);

		this.itinerario = new Itinerario();
		this.itinerarios = null;
		this.cidadeID = null;
	}

	public void remove(Itinerario itinerario) {
		ItinerarioRepository repository = new ItinerarioRepository(
				this.getManager());
		repository.remove(itinerario);

		this.itinerario = null;
	}

	public Long getCount() {
		BarcoRepository repository = new BarcoRepository(this.getManager());
		return repository.getCountBarcos();
	}

	public String preparaAlterar(Itinerario itinerario) {
		this.setItinerario(itinerario);
		this.setCidadeID(this.getItinerario().getDestino().getId());

		return "index?faces-redirect=true";
	}

	public Itinerario getItinerarioSelecionado() {
		return itinerarioSelecionado;
	}

	public void setItinerarioSelecionado(Itinerario itinerarioSelecionado) {
		this.itinerarioSelecionado = itinerarioSelecionado;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public Long getCidadeID() {
		return cidadeID;
	}

	public void setCidadeID(Long cidadeID) {
		this.cidadeID = cidadeID;
	}

	@SuppressWarnings("unchecked")
	public List<Itinerario> getItinerarios() {
		if (itinerarios == null) {
			ItinerarioRepository repository = new ItinerarioRepository(
					this.getManager());
			itinerarios = repository.getItinerarios();
		}
		return this.itinerarios;
	}

	public void setItinerarios(List<Itinerario> itinerarios) {
		this.itinerarios = itinerarios;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}
}
