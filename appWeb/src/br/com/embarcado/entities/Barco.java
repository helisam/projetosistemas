package br.com.embarcado.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BARCO")
@NamedQueries({
		@NamedQuery(name = "Barco.findAll", query = "SELECT b FROM Barco b"),
		@NamedQuery(name = "Barco.count", query = "SELECT COUNT(b) FROM Barco b"),
		@NamedQuery(name = "Barco.findByNome", query = "SELECT b FROM Barco b WHERE b.nome = :nome") })
public class Barco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "barco_ID")
	private Long id;

	@Column(name = "barco_NOME", nullable = false)
	private String nome;

	@Column(name = "barco_ANOFABRICACAO", nullable = false)
	private String anoFabricacao;

	@Column(name = "barco_CAPACIDADE", nullable = false)
	private String capacidade;

	@Column(name = "barco_DESCRICAO", nullable = false)
	private String descricao;

	@OneToOne
	@JoinColumn(name = "proprietario_ID")
	private Proprietario proprietario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(String capacidade) {
		this.capacidade = capacidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}


}
