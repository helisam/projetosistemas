package br.com.embarcado.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LinkBean {
	public String linkBarco() {
		return "linkBarco.xhtml?faces-redirect=true";
	}

	public String linkProprietario() {
		return "linkProprietario?faces-redirect=true";
	}

	public String linkItinerario() {
		return "linkItinerario?faces-redirect=true";
	}

	public String linkPassagem() {
		return "linkPassagem?faces-redirect=true";
	}

	public String listaLinkTrajeto() {
		return "index?faces-redirect=true";
	}
}
