package br.com.embarcado.managedbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import br.com.embarcado.entities.Cidade;
import br.com.embarcado.entities.Estado;
import br.com.embarcado.entities.Foto;
import br.com.embarcado.repository.CidadeRepository;
import br.com.embarcado.repository.EstadoRepository;

/**
 * @author helisam.bentes
 *
 */
@ManagedBean
@SessionScoped
public class CidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * AJEITAR ESSA CONSTANTE MÁGINA
	 * -------------------------------------------------------
	 */
	File file = new File("C:/projetos de sistemas/appWeb");
	/* ----------------------------------------------------- */

	private Cidade cidade = new Cidade();
	private List<Cidade> cidades = null;
	private Cidade cidadeSelecionada;
	private Long estadoID;
	private String descFoto;
	private String fotoSelecionada;

	private StreamedContent imagemEnviada = null;

	public void save() {
		CidadeRepository cidadeRepository = new CidadeRepository(
				this.getManager());
		EstadoRepository estRepository = new EstadoRepository(this.getManager());
		Estado estado = estRepository.find(estadoID);

		this.cidade.setEstado(estado);

		cidadeRepository.save(this.cidade);

		this.cidade = new Cidade();
		this.cidades = null;
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

	public StreamedContent getImagemEnviada() {
		return imagemEnviada;
	}

	public void setImagemEnviada(StreamedContent imagemEnviada) {
		this.imagemEnviada = imagemEnviada;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}

	public void saveFoto() {
		if (fotoSelecionada != null) {
			// Salva foto
			Foto fotoNova = new Foto();

			fotoNova.setDescricao(descFoto);
			fotoNova.setImagem(fotoSelecionada);
			fotoNova.setCidade(cidadeSelecionada);
			cidadeSelecionada.getFotos().add(fotoNova);
			getManager().merge(cidadeSelecionada);
			getManager().flush();
			// limpa variaveis
		}
	}

	public String getFotoSelecionada() {
		return fotoSelecionada;
	}

	public String getDescFoto() {
		return descFoto;
	}

	public void setDescFoto(String descFoto) {
		this.descFoto = descFoto;
	}

	public List<Foto> getFotos(Cidade cidade) {
		if (cidade != null && cidade.getFotos() != null) {
			return new ArrayList<Foto>(cidade.getFotos());
		}
		return new ArrayList<Foto>();
	}

	/*
	 * public StreamedContent getImage2(Foto foto) { try { final BufferedImage
	 * bi = ImageIO.read(new ByteArrayInputStream(foto .getImagem()));
	 * ImageIO.write(bi, "jpg", new File("C:\\out.jpg")); } catch (IOException
	 * e) { e.printStackTrace(); } return null; }
	 */

	public String getImage(Foto foto) {
		System.out.println(foto.getImagem());
		return foto.getImagem();
	}

	/*
	 * public void enviarImagem(FileUploadEvent event) { byte[] img =
	 * event.getFile().getContents(); setImagemEnviada(new
	 * DefaultStreamedContent(new ByteArrayInputStream( img), "image/jpg"));
	 * fotoSelecionada = img; }
	 */

	public void fileUploadAction(FileUploadEvent event) {
		try {
			// ExternalContext externalContext =
			// FacesContext.getCurrentInstance()
			// .getExternalContext();
			// HttpServletResponse response = (HttpServletResponse)
			// externalContext
			// .getResponse();
			//
			// FacesContext aFacesContext = FacesContext.getCurrentInstance();
			// ServletContext context = (ServletContext) aFacesContext
			// .getExternalContext().getContext();
			//
			// String realPath = context.getRealPath("/");
			// System.out.println("realPath: " + realPath);
			//
			// Aqui cria o diretorio caso não exista

			file.mkdirs();

			byte[] arquivo = event.getFile().getContents();
			String caminho = file + "/WebContent/upload/"
					+ event.getFile().getFileName();
			
			// esse trecho grava o arquivo no diretório
			FileOutputStream fos = new FileOutputStream(caminho);
			fos.write(arquivo);
			fos.close();

			// esse trecho grava apenas o nome da foto
			String caminhoLess = event.getFile().getFileName();
			fotoSelecionada = caminhoLess;

		} catch (Exception ex) {
			System.out.println("Erro no upload de imagem: " + ex);
		}
	}

}
