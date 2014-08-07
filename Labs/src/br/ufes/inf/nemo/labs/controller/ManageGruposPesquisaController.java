package br.ufes.inf.nemo.labs.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.ufes.inf.nemo.labs.application.ManageGruposPesquisaService;
import br.ufes.inf.nemo.labs.application.ManageInstituicoesEnsinoService;
import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.labs.domain.GrupoPesquisa;
import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;
import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.labs.domain.Pessoa;
import br.ufes.inf.nemo.labs.persistence.GrupoPesquisaDAO;
import br.ufes.inf.nemo.sparql.DBLP;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.SimpleFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.PersistentObjectConverterFromId;

@Named
@SessionScoped
public class ManageGruposPesquisaController extends CrudController<GrupoPesquisa> {
	
	@EJB
	private ManageGruposPesquisaService managegruposPesquisaService;
	
	@EJB
	private GrupoPesquisaDAO grupoPesquisaDAO;
	
	private PersistentObjectConverterFromId<GrupoPesquisa> grupoPesquisaConverter;
	
	private List<GrupoPesquisa> gruposPesquisa;
	
	private List<Artigo> artigos;
	
	private List<LinhaPesquisa> linhasPesquisa;
	
	private List<Pessoa> membros;
	
	private String nome;
	

	public ManageGruposPesquisaController(){
		viewPath = "/manageGruposPesquisa/";
		bundleName = "msgs";
	}

	@Override
	protected GrupoPesquisa createNewEntity() {
		// TODO Auto-generated method stub
		return new GrupoPesquisa();
	}

	@Override
	protected CrudService<GrupoPesquisa> getCrudService() {
		// TODO Auto-generated method stub
		return managegruposPesquisaService;
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		addFilter(new SimpleFilter("manageGruposPesquisa.filter.byNome", "nome", 
				getI18nMessage("msgs", "manageGruposPesquisa.text.filter.byNome")));

	}
	
	public Converter getGrupoPesquisaConverter(){
		if(grupoPesquisaConverter == null) {
			grupoPesquisaConverter = new PersistentObjectConverterFromId<GrupoPesquisa>(grupoPesquisaDAO);
		}
		return grupoPesquisaConverter;
	}
	
	public List<GrupoPesquisa> getGruposPesquisa(){
		gruposPesquisa = new ArrayList<GrupoPesquisa>(managegruposPesquisaService.getGruposPesquisa());
		return gruposPesquisa;
	}
	
	public List<Artigo> getArtigos(){
		artigos = new ArrayList<Artigo>(DBLP.getArtigos(selectedEntity.getMembros(), selectedEntity.getAnoFundacao()));
		return artigos;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		GrupoPesquisa gp = grupoPesquisaDAO.retrieveByNome(nome);
		selectedEntity = gp;
		
	}
	
	
	public List<LinhaPesquisa> getLinhasPesquisa() {
		Set<LinhaPesquisa> set = selectedEntity.getLinhasPesquisa();
		linhasPesquisa = new ArrayList<LinhaPesquisa>();
		linhasPesquisa.addAll(set);
		return linhasPesquisa;
	}

	public List<Pessoa> getMembros() {
		Set<Pessoa> set = selectedEntity.getMembros();
		membros = new ArrayList<Pessoa>();
		membros.addAll(set);
		return membros;
	}

	public void createRdfGrupoPesquisa(){
		GrupoPesquisa gp = grupoPesquisaDAO.retrieveByNome(nome);
		selectedEntity = gp;
		managegruposPesquisaService.createRdfPessoa(selectedEntity);
	}
	
	public void suggestUri(){
		
		String nome = selectedEntity.getNome();
		
		nome = selectedEntity.getNome();
		nome = nome.trim();
		nome = URLEncoder.encode(nome);
		nome = nome.replace("+", "%20");
		String uriLocal = "http://localhost:8080/Labs/grupo/" + nome;
		selectedEntity.setLocalUri(uriLocal);
		
	}
	

}
