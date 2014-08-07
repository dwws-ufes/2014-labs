package br.ufes.inf.nemo.labs.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class LinhaPesquisa extends PersistentObjectSupport implements Comparable<LinhaPesquisa> {

	
	private String titulo;
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	@Override
	public int compareTo(LinhaPesquisa o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
