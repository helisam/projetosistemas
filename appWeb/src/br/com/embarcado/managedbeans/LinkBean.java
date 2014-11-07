package br.com.embarcado.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LinkBean {
	public String linkBarco() {
		return "/barco";
	}

	public String linkProprietario() {
		return "/proprietario";
	}

	public String linkItinerario() {
		return "/itinerario";
	}

	public String linkPassagem() {
		return "/passagem";
	}
	
	public String linkCidade() {
		return "/cidade";
	}

	public String listaLinkTrajeto() {
		return "index?faces-redirect=true";
	}
	
	public String linkHome() {
		return "/index.xhtml";
	}
	
	 public String logout() {
	        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	        return "/login.xhtml?faces-redirect=true";
	    }
}
