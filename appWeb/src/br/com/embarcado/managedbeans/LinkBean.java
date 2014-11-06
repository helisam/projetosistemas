package br.com.embarcado.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LinkBean {
	public String linkBarco() {
		return "barco?faces-redirect=true";
	}

	public String linkProprietario() {
		return "proprietario?faces-redirect=true";
	}

	public String linkItinerario() {
		return "itinerario?faces-redirect=true";
	}

	public String linkPassagem() {
		return "passagem?faces-redirect=true";
	}

	public String listaLinkTrajeto() {
		return "index?faces-redirect=true";
	}
}
