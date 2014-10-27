package br.com.embarcado.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ITINERARIO")
@NamedQueries({
		@NamedQuery(name = "Itinerario.findAll", query = "SELECT i FROM Itinerario i"),
		@NamedQuery(name = "Itinerario.count", query = "SELECT COUNT(i) FROM Itinerario i"),
		@NamedQuery(name = "Itinerario.findByDestino", query = "SELECT i FROM Itinerario i WHERE i.destino = :destino") })
public class Itinerario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itinerario_ID")
	private Long id;

	@Column(name = "itinerario_DESTINO", length = 50, nullable = false)
	private String destino;

	@Column(name = "itinerario_LOCALPARTIDA", nullable = false)
	private String localPartida;

	@Column(name = "itinerario_HORASAIDA", nullable = false)
	private String horaSaida;

	@Column(name = "itinerario_HORACHEGADA", nullable = false)
	private String horaChegada;

	@Column(name = "itinerario_DISTANCIA", nullable = false)
	private String distancia;

	@Column(name = "itinerario_OBSERVACAO", nullable = true)
	private String observacao;

	@Column(name = "itinerario_DATA", nullable = false)
	private String data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getLocalPartida() {
		return localPartida;
	}

	public void setLocalPartida(String localPartida) {
		this.localPartida = localPartida;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
