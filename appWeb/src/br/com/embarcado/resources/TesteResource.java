package br.com.embarcado.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
public class TesteResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String showHelloWorld() {
		System.out.println("Entrou hello");
		return "Ol� mundo!";
	}
}
