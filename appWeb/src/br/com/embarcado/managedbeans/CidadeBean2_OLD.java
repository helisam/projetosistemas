package br.com.embarcado.managedbeans;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import br.com.embarcado.dao.CidadeDAO;
import br.com.embarcado.dao.FotoDAO;
import br.com.embarcado.entities.Cidade;
import br.com.embarcado.entities.Foto;

@ManagedBean
@SessionScoped
public class CidadeBean2_OLD implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Cidade> cidades;
	private Cidade cidade = new Cidade();
	private Cidade cidadeSelecionada = new Cidade();
	private CidadeDAO cidadeDAO;

	private List<Foto> fotos;
	private Foto foto = new Foto();
	private FotoDAO fotoDAO = new FotoDAO();

	public CidadeBean2_OLD() {
		cidadeDAO = new CidadeDAO();
		fotoDAO = new FotoDAO();

		cidades = cidadeDAO.listAll();
	}

	public void salvaCidade() {

		try {
			cidadeDAO.save(cidade);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cidade = new Cidade();
			cidades = cidadeDAO.listAll();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Cidade adicionada", "Cidade adicionada"));
		}
	}

	public void salvaFoto() {
		try {
			fotoDAO.save(foto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			foto = new Foto();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Foto adicionada", "Foto adicionada"));
		}
	}

	/*public void processFileUpload(FileUploadEvent uploadEvent) {

		try {
			foto.setCidade(cidadeSelecionada);
			foto.setImagem(uploadEvent.getFile().getContents());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}*/
	
	public void listaFotosCidade(){
		try {
			ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			fotos = fotoDAO.listByProdutos(cidadeSelecionada.getId());
			
			File folder = new File(sContext.getRealPath("/temp"));
			if(!folder.exists())
				folder.mkdirs();
			
			for (Foto f : fotos) {
				String nomeArquivo = f.getId() + ".jpg";
				String arquivo = sContext.getRealPath("/temp") + File.separator + nomeArquivo;
				
//				criaArquivo(f.getImagem(), arquivo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void criaArquivo (byte[] bytes, String arquivo){
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
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
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

	public CidadeDAO getCidadeDAO() {
		return cidadeDAO;
	}

	public void setCidadeDAO(CidadeDAO cidadeDAO) {
		this.cidadeDAO = cidadeDAO;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public FotoDAO getFotoDAO() {
		return fotoDAO;
	}

	public void setFotoDAO(FotoDAO fotoDAO) {
		this.fotoDAO = fotoDAO;
	}
	
	
}
