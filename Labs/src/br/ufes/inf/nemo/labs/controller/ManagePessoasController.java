package br.ufes.inf.nemo.labs.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.ufes.inf.nemo.labs.application.ManagePessoasService;
import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.labs.domain.Pessoa;
import br.ufes.inf.nemo.labs.persistence.PessoaDAO;
import br.ufes.inf.nemo.sparql.DBLP;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.SimpleFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.PersistentObjectConverterFromId;

@Named 
@SessionScoped
public class ManagePessoasController extends CrudController<Pessoa> {
	
	@EJB
	private ManagePessoasService managePessoasService;
	
	@EJB
	private PessoaDAO pessoaDAO;
	
	private PersistentObjectConverterFromId<Pessoa> pessoaConverter;
	
	private List<Pessoa> pessoas;
	
	private List<Artigo> artigos;
	
	private List<LinhaPesquisa> linhasPesquisa;
	
	
	private String nome;
	
	public ManagePessoasController(){
		viewPath = "/managePessoas/";
		bundleName = "msgs";
	}

	@Override
	protected Pessoa createNewEntity() {
		// TODO Auto-generated method stub
		return new Pessoa();
	}

	@Override
	protected CrudService<Pessoa> getCrudService() {
		// TODO Auto-generated method stub
		return managePessoasService;
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		addFilter(new SimpleFilter("managePessoas.filter.byNome", "nome", 
				getI18nMessage("msgs", "managePessoas.text.filter.byNome")));
	}
	
	
	public void suggestUri(){
		
		String nome = selectedEntity.getNome();
		String uriDBLP = selectedEntity.getUriDBLP();
		
		if((nome != null) && ((uriDBLP == null) || (uriDBLP.length() == 0))){
			nome = nome.trim();
			nome = nome.replace(" ", "_");
			nome = URLEncoder.encode(nome);
			uriDBLP = "http://dblp.l3s.de/d2r/resource/authors/" + nome;
			selectedEntity.setUriDBLP(uriDBLP);
		}
		
		nome = selectedEntity.getNome();
		nome = nome.trim();
		nome = URLEncoder.encode(nome);
		nome = nome.replace("+", "%20");
		String uriLocal = "http://localhost:8080/Labs/pessoa/" + nome;
		selectedEntity.setLocalUri(uriLocal);
		
	}
	
	public Converter getPessoaConverter(){
		if(pessoaConverter == null){
			pessoaConverter = new PersistentObjectConverterFromId<Pessoa>(pessoaDAO);
		}
		return pessoaConverter;
	}
	
	public List<Pessoa> getPessoas(){
		pessoas = new ArrayList<Pessoa>(managePessoasService.getPessoas());
		return pessoas;
	}
	
	public List<Artigo> getArtigos(){
		artigos = new ArrayList<Artigo>(DBLP.getArtigos(selectedEntity.getUriDBLP(), 0));
		return artigos;
	}
	
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		System.out.println("\nSET NOME\n");
		this.nome = nome;
		Pessoa p = pessoaDAO.retrieveByNome(nome);
		selectedEntity = p;
		p.setArtigos(getArtigos());
		selectedEntity = p;
	}
	
	public List<LinhaPesquisa> getLinhasPesquisa() {
		Set<LinhaPesquisa> set = selectedEntity.getLinhasPesquisa();
		linhasPesquisa = new ArrayList<LinhaPesquisa>();
		linhasPesquisa.addAll(set);
		return linhasPesquisa;
	}

	public void downloadRdfPessoa(){
		//System.out.println("\nRDF\n");
		Pessoa p = pessoaDAO.retrieveByNome(nome);
		selectedEntity = p;
		p.setArtigos(getArtigos());
		selectedEntity = p;
		managePessoasService.downloadRdfPessoa(selectedEntity);
	}
	

}