package br.com.embarcado.managedbeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;

import br.com.embarcado.entities.Cidade;
import br.com.embarcado.entities.Estado;
import br.com.embarcado.repository.CidadeRepository;
import br.com.embarcado.repository.EstadoRepository;

@ManagedBean
@SessionScoped
public class CidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cidade cidade = new Cidade();
	private List<Cidade> cidades = null;
	private Cidade cidadeSelecionada;
	private Long estadoID;

	public void save() {
		EstadoRepository estRepository = new EstadoRepository(this.getManager());
		Estado estado = estRepository.find(estadoID);
		CidadeRepository cidadeRepository = new CidadeRepository(
				this.getManager());
		this.cidade.setEstado(estado);

		cidadeRepository.save(this.cidade);

		this.cidade = new Cidade();
		this.cidades = null;
	}

	/*
	 * public void salvaFoto() { try { fotoDAO.save(foto); } catch (Exception e)
	 * { e.printStackTrace(); } finally { foto = new Foto();
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, "Foto adicionada",
	 * "Foto adicionada")); } }
	 */

	public void processFileUpload(FileUploadEvent uploadEvent) {

		try {
			cidadeSelecionada = new Cidade();
			cidadeSelecionada.setImagem(uploadEvent.getFile().getContents());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * public void listaFotosCidade(){
	 * 
	 * try { ServletContext sContext = (ServletContext)
	 * FacesContext.getCurrentInstance().getExternalContext().getContext();
	 * fotos = fotoDAO.listByProdutos(cidadeSelecionada.getId());
	 * 
	 * File folder = new File(sContext.getRealPath("/temp"));
	 * if(!folder.exists()) folder.mkdirs();
	 * 
	 * for (Foto f : fotos) { String nomeArquivo = f.getId() + ".jpg"; String
	 * arquivo = sContext.getRealPath("/temp") + File.separator + nomeArquivo;
	 * 
	 * criaArquivo(f.getImagem(), arquivo); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	@SuppressWarnings("unchecked")
	public void listaFotosCidades() {
		ServletContext sContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		CidadeRepository repository = new CidadeRepository(this.getManager());
		cidades = (List<Cidade>) repository.find(this.cidade.getId());
		File folder = new File(sContext.getRealPath("/temp"));
		if (!folder.exists())
			folder.mkdirs();

		for (Cidade cidade : cidades) {
			String nomeArquivo = cidade.getId() + ".jpg";
			String arquivo = sContext.getRealPath("/temp") + File.separator
					+ nomeArquivo;

			criaArquivo(cidade.getImagem(), arquivo);
		}
	}

	public void criaArquivo(byte[] bytes, String arquivo) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> getFotosCidade() {
		if (cidades == null) {
			CidadeRepository repository = new CidadeRepository(
					this.getManager());
			cidades = repository.getCidades();
		}
		return this.cidades;
	}

	public void update() {
		EstadoRepository estRepository = new EstadoRepository(this.getManager());
		Estado estado = estRepository.find(estadoID);
		this.cidade.setEstado(estado);

		CidadeRepository repository = new CidadeRepository(this.getManager());
		repository.update(this.cidade);

		this.cidade = new Cidade();
		this.cidades = null;
		this.estadoID = null;
	}

	public void remove(Cidade cidade) {
		CidadeRepository repository = new CidadeRepository(this.getManager());
		repository.remove(cidade);

		this.cidades = null;
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> getCidades() {
		if (cidades == null) {
			CidadeRepository repository = new CidadeRepository(
					this.getManager());
			cidades = repository.getCidades();
		}
		return this.cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Long getCount() {
		CidadeRepository repository = new CidadeRepository(this.getManager());
		return repository.getCountCidades();
	}

	public String preparaAlterar(Cidade cidade) {
		this.setCidade(cidade);
		this.setEstadoID(this.getCidade().getEstado().getId());

		return "index?faces-redirect=true";
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Long getEstadoID() {
		return estadoID;
	}

	public void setEstadoID(Long estadoID) {
		this.estadoID = estadoID;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}
}
