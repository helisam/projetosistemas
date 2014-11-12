/*package br.com.embarcado.managedbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.embarcado.entities.Foto;
import br.com.embarcado.repository.BarcoRepository;
import br.com.embarcado.repository.FotoRepository;

@ManagedBean
@SessionScoped
public class FotoBean {

	 private UploadedFile arquivo; 
	
	private Foto foto = new Foto();
	private List<Foto> fotos = null;
	private Logger logger = LoggerFactory.getLogger(FotoBean.class);

	public void save() {
		FotoRepository repository = new FotoRepository(this.getManager());
		repository.save(this.foto);

		this.foto = new Foto();
		this.fotos = null;
	}

	public void update() {
		FotoRepository repository = new FotoRepository(this.getManager());
		repository.find(this.foto.getId());
		repository.update(this.foto);

		this.foto = new Foto();
		this.fotos = null;
	}

	public void remove(Foto foto) {
		FotoRepository repository = new FotoRepository(this.getManager());
		repository.remove(foto);

		this.foto = null;
	}

	public Long getCount() {
		BarcoRepository repository = new BarcoRepository(this.getManager());
		return repository.getCountBarcos();
	}

	public String preparaAlterar(Foto foto) {
		this.setFoto(foto);

		return "index?faces-redirect=true";
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	@SuppressWarnings("unchecked")
	public List<Foto> getFotos() {
		if (fotos == null) {
			FotoRepository repository = new FotoRepository(this.getManager());
			fotos = repository.getFotos();
		}
		return this.fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}

	public void handleFileUpload(FileUploadEvent event) {
		logger.info("Uploaded: {}", event.getFile().getFileName());

		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		byte[] imagem = event.getFile().getContents();
		foto.setImagem(imagem);

		
		 * this.factory = Persistence.createEntityManagerFactory(null);
		 * 
		 * EntityManager entityManager = this.factory.createEntityManager();
		 * entityManager.getTransaction().begin();
		 * 
		 * ImagemRepository img = new ImagemRepository(entityManager);
		 

		FotoRepository repository = new FotoRepository(this.getManager());
		repository.save(this.foto);

		this.foto = new Foto();
		this.fotos = null;
	}

	@SuppressWarnings("unused")
	public void fileUploadAction(FileUploadEvent event) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext
					.getResponse();

			FacesContext aFacesContext = FacesContext.getCurrentInstance();
			ServletContext context = (ServletContext) aFacesContext
					.getExternalContext().getContext();

			String realPath = context.getRealPath("/");

			// Aqui cria o diretorio caso não exista
			File file = new File(realPath + "/fotos/");
			file.mkdirs();

			byte[] arquivo = event.getFile().getContents();
			String caminho = realPath + "/fotos/"
					+ event.getFile().getFileName();

			// esse trecho grava o arquivo no diretório
			FileOutputStream fos = new FileOutputStream(caminho);
			fos.write(arquivo);
			fos.close();

		} catch (Exception ex) {
			System.out.println("Erro no upload de imagem" + ex);
		}
	}

	public void fileUploadAction2(FileUploadEvent event) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext
					.getResponse();

			FacesContext aFacesContext = FacesContext.getCurrentInstance();
			ServletContext context = (ServletContext) aFacesContext
					.getExternalContext().getContext();

			String realPath = context.getRealPath("/");

			// Aqui cria o diretorio caso não exista
			File file = new File(realPath + "/fotos/");
			file.mkdirs();

			byte[] arquivo = event.getFile().getContents();
			String caminho = realPath + "/fotos/"
					+ event.getFile().getFileName();

			// esse trecho grava o arquivo no diretório
			FileOutputStream fos = new FileOutputStream(caminho);
			fos.write(arquivo);
			fos.close();

		} catch (Exception ex) {
			System.out.println("Erro no upload de imagem" + ex);
		}
	}
	public void upload(){
		 
        try{
 
            if(arquivo != null) {
 
                //E necessario informar um local para gravar a imagem, 
                //sera na pasta upload dentro do nosso projeto
                ServletContext servletContext = (ServletContext)
                FacesContext.getCurrentInstance().getExternalContext().getContext();
                String local = servletContext.getRealPath("upload");
 
                //obtemos o nome da imagem
                candidato.setFoto(arquivo.getFileName());
 
                //gravamos a imagem
                InputStream input = arquivo.getInputstream();
                FileOutputStream fos = new 
                    FileOutputStream(local+File.separator+arquivo.getFileName());
 
                System.out.println(local+File.separator+arquivo.getFileName());
 
                int len;
                byte[] buffer = new byte[1024];
                while ((len = input.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                }
 
                //fechamos o arquivo
                input.close();
                fos.flush();
                fos.close();
            }
 
        }catch(Exception x){
            x.printStackTrace();
        }
    } 

}
*/