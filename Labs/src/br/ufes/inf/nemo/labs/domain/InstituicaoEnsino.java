package br.ufes.inf.nemo.labs.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class InstituicaoEnsino extends PersistentObjectSupport implements Comparable<InstituicaoEnsino> {

	private String nome;
	private String sigla;
	private String uri;
	
	@Transient
	private String site;
	@Transient
	private String sede;
	@Transient
	private String reitor;
	
	@OneToMany(mappedBy = "instituicaoEnsino")
	private Set<GrupoPesquisa> gruposPesquisa;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getReitor() {
		return reitor;
	}
	public void setReitor(String reitor) {
		this.reitor = reitor;
	}
	public Set<GrupoPesquisa> getGruposPesquisa() {
		return gruposPesquisa;
	}
	public void setGruposPesquisa(Set<GrupoPesquisa> gruposPesquisa) {
		this.gruposPesquisa = gruposPesquisa;
	}
	@Override
	public int compareTo(InstituicaoEnsino o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
