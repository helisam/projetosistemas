package br.com.embarcado.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.embarcado.entities.Proprietario;
import br.com.embarcado.repository.ProprietarioRepository;

@ManagedBean
@SessionScoped
public class ProprietarioBean {

	//@ManagedProperty(value = "#{entityManager}")
	//private EntityManager entityManager;

	private Proprietario proprietario = new Proprietario();
	private List<Proprietario> proprietarios;
	private Long pID;

	public void save() {
		ProprietarioRepository repository = new ProprietarioRepository(
				this.getManager());
		repository.save(this.proprietario);

		this.proprietario = new Proprietario();
		this.proprietarios = null;
	}

	public void remove(Proprietario prop) {
		ProprietarioRepository repository = new ProprietarioRepository(
				this.getManager());
		repository.remove(prop);

		this.proprietarios = null;
	}
	
	public String preparaAlterar(Proprietario proprietario){
		this.setProprietario(proprietario);
		this.setpID(getProprietario().getId());
		
		return "index?faces-redirect=true";
	}
	
	public void update(){
		ProprietarioRepository repository = new ProprietarioRepository(this.getManager());
		repository.search(this.proprietario.getId());
		repository.update(this.proprietario);
		
		this.proprietario = new Proprietario();
		this.proprietarios = null;
	}

	@SuppressWarnings("unchecked")
	public List<Proprietario> getProprietarios() {
		if (this.proprietarios == null) {
			ProprietarioRepository repository = new ProprietarioRepository(
					this.getManager());
			this.proprietarios = repository.getProprietarios();
		}

		return this.proprietarios;
	}

	public void setProprietarios(List<Proprietario> proprietarios) {
		this.proprietarios = proprietarios;
	}

	public Long getCount() {
		ProprietarioRepository repository = new ProprietarioRepository(
				this.getManager());
		return repository.getCountProprietarios();
	}
	
	public Long getpID() {
		return pID;
	}

	public void setpID(Long pID) {
		this.pID = pID;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

//	public void setEntityManager(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
	
	private EntityManager getManager() {
	    FacesContext fc = FacesContext.getCurrentInstance();
	    ExternalContext ec = fc.getExternalContext();
	    HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    return (EntityManager) request.getAttribute("entityManager");
	}
}