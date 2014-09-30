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
@Table(name = "PROPRIETARIO")
@NamedQueries({
	@NamedQuery(name = "Proprietario.findAll", query = "SELECT p FROM Proprietario p"),
	@NamedQuery(name = "Proprietario.count", query = "SELECT COUNT(p) FROM Proprietario p"),
	@NamedQuery(name = "Proprietario.findByNome", query = "SELECT p FROM Proprietario p WHERE p.nome = :nome")
})
public class Proprietario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proprietario_ID")
	private Long id;
	
	@Column(name = "proprietario_NOME", nullable = false)
	private String nome;
	
	@Column(name = "proprietario_CPF" , nullable = false)
	private String cpf;
	
	@Column(name = "proprietario_MAIL", nullable = false)
	private String mail;
	
	@Column(name = "proprietario_FONE", nullable = false)
	private String fone;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	
}
