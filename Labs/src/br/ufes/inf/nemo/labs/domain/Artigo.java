package br.ufes.inf.nemo.labs.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

public class Artigo extends PersistentObjectSupport implements Comparable<Artigo> {
	
	private String titulo;
	private String uri;
	private Integer anoPublicacao;
	private String link;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public int compareTo(Artigo o) {
		// TODO Auto-generated method stub
		if(this.anoPublicacao < o.anoPublicacao){
			return 1;
		}
		if(this.anoPublicacao > o.anoPublicacao){
			return -1;
		}
		
		return this.titulo.compareTo(o.titulo);
	}

}
