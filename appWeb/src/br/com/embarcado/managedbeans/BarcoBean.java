package br.com.embarcado.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.embarcado.entities.Barco;
import br.com.embarcado.entities.Itinerario;
import br.com.embarcado.entities.Proprietario;
import br.com.embarcado.repository.BarcoRepository;
import br.com.embarcado.repository.ItinerarioRepository;
import br.com.embarcado.repository.ProprietarioRepository;

@ManagedBean
@SessionScoped
public class BarcoBean {

	private Barco barcoSelecionado;
	
	public Barco getBarcoSelecionado() {
		return barcoSelecionado;
	}

	public void setBarcoSelecionado(Barco barcoSelecionado) {
		this.barcoSelecionado = barcoSelecionado;
	}

	private Barco barco = new Barco();
	private List<Barco> barcos = null;
	private Long proprietarioID;
	private Long itinerarioID;
	
	public void save() {
		ProprietarioRepository propRepository = new ProprietarioRepository(
				this.getManager());
		Proprietario proprietario = propRepository.search(proprietarioID);
		
		ItinerarioRepository itiRepository = new ItinerarioRepository(this.getManager());
		Itinerario itinerario = itiRepository.search(itinerarioID); 
		this.barco.setProprietario(proprietario);
		this.barco.setItinerario(itinerario);

		BarcoRepository barcoRepository = new BarcoRepository(this.getManager());
		barcoRepository.save(this.barco);

		this.barco = new Barco();
		this.barcos = null;
	}

	public void update() {
		ProprietarioRepository propRepository = new ProprietarioRepository(
				this.getManager());
		Proprietario proprietario = propRepository.search(proprietarioID);
		ItinerarioRepository itiRepository = new ItinerarioRepository(this.getManager());
		Itinerario itinerario = itiRepository.search(itinerarioID);
		this.barco.setProprietario(proprietario);
		this.barco.setItinerario(itinerario);

		BarcoRepository barcoRepository = new BarcoRepository(this.getManager());
		barcoRepository.update(this.barco);

		this.barco = new Barco();
		this.barcos = null;
		this.proprietarioID = null;
		this.itinerarioID = null;
	}

	public void remove(Barco barco) {
		BarcoRepository repository = new BarcoRepository(this.getManager());
		repository.remove(barco);

		this.barcos = null;
	}

	public List<Barco> getBarcos() {
		if (barcos == null) {
			BarcoRepository repository = new BarcoRepository(this.getManager());
			barcos = repository.getBarcos();
		}
		return this.barcos;
	}
	
	public void setBarcos(List<Barco> barcos) {
		this.barcos = barcos;
	}

	public Long getCount() {
		BarcoRepository repository = new BarcoRepository(this.getManager());
		return repository.getCountBarcos();
	}

	public String preparaAlterar(Barco barco) {
		this.setBarco(barco);
		this.setProprietarioID(this.getBarco().getProprietario().getId());
		this.setItinerarioID(this.getBarco().getItinerario().getId());
		
		return "index?faces-redirect=true";
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public Long getProprietarioID() {
		return proprietarioID;
	}

	public void setProprietarioID(Long proprietarioID) {
		this.proprietarioID = proprietarioID;
	}
	
	public Long getItinerarioID() {
		return itinerarioID;
	}

	public void setItinerarioID(Long itinerarioID) {
		this.itinerarioID = itinerarioID;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}
}
