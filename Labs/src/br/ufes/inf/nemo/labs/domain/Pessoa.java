package br.ufes.inf.nemo.labs.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Pessoa extends PersistentObjectSupport implements Comparable<Pessoa> {
	
	private String nome;
	private String uriDBLP;
	private String localUri;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<LinhaPesquisa> linhasPesquisa;
	
	@Transient
	private List<Artigo> artigos;
	
	@ManyToOne
	private GrupoPesquisa grupoPesquisa;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUriDBLP() {
		return uriDBLP;
	}
	public void setUriDBLP(String uri) {
		this.uriDBLP = uri;
	}	
	
	public String getLocalUri() {
		return localUri;
	}
	public void setLocalUri(String localUri) {
		this.localUri = localUri;
	}
	public Set<LinhaPesquisa> getLinhasPesquisa() {
		return linhasPesquisa;
	}
	public void setLinhasPesquisa(Set<LinhaPesquisa> linhasPesquisa) {
		this.linhasPesquisa = linhasPesquisa;
	}
	public List<Artigo> getArtigos() {
		return artigos;
	}
	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}
	public GrupoPesquisa getGrupoPesquisa() {
		return grupoPesquisa;
	}
	public void setGrupoPesquisa(GrupoPesquisa grupoPesquisa) {
		this.grupoPesquisa = grupoPesquisa;
	}
	@Override
	public int compareTo(Pessoa o) {
		// TODO Auto-generated method stub
		if(this.nome == null) return -1;
		if(o.nome == null) return -1;
		
		int cmp = this.nome.compareTo(o.nome);
		if(cmp != 0) return cmp;
		
		return this.uuid.compareTo(o.uuid);
	}	
}
