package br.com.embarcado.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FOTO")

public class Foto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "foto_ID")
	private long id;
	@Lob
	@Column(name = "foto_IMAGEM", columnDefinition = "LONGBLOB")
	private byte[] imagem;
	

	@Column(name = "foto_DESCRICAO")
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cidade_ID")
	private Cidade cidade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public byte[] getImagem() {
		return imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
