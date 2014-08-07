package br.ufes.inf.nemo.labs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.ufes.inf.nemo.labs.application.ManageInstituicoesEnsinoService;
import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;
import br.ufes.inf.nemo.labs.persistence.InstituicaoEnsinoDAO;
import br.ufes.inf.nemo.sparql.DBPEDIA;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.SimpleFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.PersistentObjectConverterFromId;

@Named
@SessionScoped
public class ManageInstituicoesEnsinoController extends CrudController<InstituicaoEnsino> {
	
	@EJB
	private ManageInstituicoesEnsinoService manageInstituicoesEnsinoService;
	
	@EJB
	private InstituicaoEnsinoDAO instituicaoEnsinoDAO;
	
	
	private PersistentObjectConverterFromId<InstituicaoEnsino> instituicaoEnsinoConverter;
	
	private List<InstituicaoEnsino> instituicoesEnsino;
	
	private InstituicaoEnsino informacaoExtra;
	
	
	public ManageInstituicoesEnsinoController(){
		viewPath = "/manageInstituicoesEnsino/";
		bundleName = "msgs";
	}
	
	@Override
	protected InstituicaoEnsino createNewEntity() {
		// TODO Auto-generated method stub
		return new InstituicaoEnsino();
	}

	@Override
	protected CrudService<InstituicaoEnsino> getCrudService() {
		// TODO Auto-generated method stub
		return manageInstituicoesEnsinoService;
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		addFilter(new SimpleFilter("manageInstituicoesEnsino.filter.byNome", "nome", 
				getI18nMessage("msgs", "manageInstituicoesEnsino.text.filter.byNome")));
	}
	
	public Converter getInstituicaoEnsinoConverter(){
		if(instituicaoEnsinoConverter == null){
			instituicaoEnsinoConverter = new PersistentObjectConverterFromId<InstituicaoEnsino>(instituicaoEnsinoDAO);
		}
		return instituicaoEnsinoConverter;
	}
	
	public List<InstituicaoEnsino> getInstituicoesEnsino(){
		instituicoesEnsino = new ArrayList<InstituicaoEnsino>(manageInstituicoesEnsinoService.getInstituicoesEnsino());
		
		return instituicoesEnsino;
	}
	
	public void suggestUriDBPEDIA(){
		String nome = selectedEntity.getNome();
		String uriDBPEDIA = selectedEntity.getUri();
		if((nome != null) && ((uriDBPEDIA == null) || (uriDBPEDIA.length() == 0))){
			nome = nome.trim();
			nome = nome.replace(" ", "_");
			
			uriDBPEDIA = "http://pt.dbpedia.org/resource/" + nome;
			selectedEntity.setUri(uriDBPEDIA);			
		}
	}
	
	public InstituicaoEnsino getInformacaoExtra() {
		informacaoExtra = (InstituicaoEnsino) selectedEntity;
		informacaoExtra = DBPEDIA.getInformacoes(informacaoExtra);
		
		return informacaoExtra;
	}

	public void atualizaInformacoes(){
		
		InstituicaoEnsino instituicaoEnsino = (InstituicaoEnsino) selectedEntity;
		instituicaoEnsino = DBPEDIA.getInformacoes(instituicaoEnsino);
		selectedEntity.setSite(instituicaoEnsino.getSite());
	}
	
	
	
	
}
