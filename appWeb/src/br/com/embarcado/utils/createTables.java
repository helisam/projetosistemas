package br.com.embarcado.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class createTables {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("embarco");
		emf.close();
		
	}
}
