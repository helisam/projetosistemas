package br.com.embarcado.managedbeans;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.embarcado.entities.Barco;
import br.com.embarcado.entities.Proprietario;
import br.com.embarcado.repository.BarcoRepository;
import br.com.embarcado.repository.ProprietarioRepository;

@ManagedBean
@ApplicationScoped
public class BarcoBean {

	private Barco barco = new Barco();
	private List<Barco> barcos = null;
	private Long proprietarioID;

	public void save(Barco barco) {
	
		ProprietarioRepository propRepository = new ProprietarioRepository(
				this.getManager());
		Proprietario proprietario = propRepository.search(proprietarioID);
		this.barco.setProprietario(proprietario);
		
		BarcoRepository barcoRepository = new BarcoRepository(this.getManager());
		barcoRepository.save(this.barco);

		this.barco = new Barco();
		this.barcos = null;
	}
	
	public void setBarcos(List<Barco> barcos) {
		this.barcos = barcos;
	}

	public void update() {
		ProprietarioRepository propRepository = new ProprietarioRepository(
				this.getManager());
		Proprietario proprietario = propRepository.search(proprietarioID);
		this.barco.setProprietario(proprietario);

		BarcoRepository barcoRepository = new BarcoRepository(this.getManager());
		barcoRepository.update(this.barco);

		this.barco = new Barco();
		this.proprietarioID = null;
		this.barcos = null;
	}

	public void remove(Barco barco) {
		BarcoRepository repository = new BarcoRepository(this.getManager());
		repository.remove(barco);

		this.barcos = null;
	}

	@SuppressWarnings("unchecked")
	public List<Barco> getBarcos() {
		if (this.barcos == null) {
			BarcoRepository repository = new BarcoRepository(this.getManager());
			this.barcos = repository.getBarcos();
		}
		return this.barcos;
	}

	public Long getCount() {
		BarcoRepository repository = new BarcoRepository(this.getManager());
		return repository.getCountBarcos();
	}

	public String preparaAlterar(Barco barco) {
		this.setBarco(barco);
		this.setProprietarioID(this.getBarco().getProprietario().getId());
		
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

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}
}
