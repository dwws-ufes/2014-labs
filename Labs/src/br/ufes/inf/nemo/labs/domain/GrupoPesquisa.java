package br.ufes.inf.nemo.labs.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class GrupoPesquisa extends PersistentObjectSupport implements Comparable<GrupoPesquisa> {
	
	private String nome;
	private String sigla;
	private String localUri;
	
	private Integer anoFundacao;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<LinhaPesquisa> linhasPesquisa;

	@ManyToOne
	private InstituicaoEnsino instituicaoEnsino;
	
	@OneToMany(mappedBy="grupoPesquisa", fetch=FetchType.EAGER)
	private Set<Pessoa> membros;
	
	

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

	public String getLocalUri() {
		return localUri;
	}

	public void setLocalUri(String localUri) {
		this.localUri = localUri;
	}

	public Integer getAnoFundacao() {
		return anoFundacao;
	}

	public void setAnoFundacao(Integer anoFundacao) {
		this.anoFundacao = anoFundacao;
	}

	public InstituicaoEnsino getInstituicaoEnsino() {
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(InstituicaoEnsino instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

	public Set<Pessoa> getMembros() {
		return membros;
	}

	public void setMembros(Set<Pessoa> membros) {
		this.membros = membros;
	}

	public Set<LinhaPesquisa> getLinhasPesquisa() {
		return linhasPesquisa;
	}

	public void setLinhasPesquisa(Set<LinhaPesquisa> linhasPesquisa) {
		this.linhasPesquisa = linhasPesquisa;
	}

	@Override
	public int compareTo(GrupoPesquisa o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
