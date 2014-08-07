package br.ufes.inf.nemo.labs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.ufes.inf.nemo.labs.application.ManageLinhasPesquisaService;
import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;
import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.labs.persistence.InstituicaoEnsinoDAO;
import br.ufes.inf.nemo.labs.persistence.LinhaPesquisaDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.SimpleFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.PersistentObjectConverterFromId;

@Named
@SessionScoped
public class ManageLinhasPesquisaController extends CrudController<LinhaPesquisa> {
	
	@EJB
	private ManageLinhasPesquisaService manageLinhasPesquisaService;
	
	@EJB
	private LinhaPesquisaDAO linhaPesquisaDAO;
	
	private PersistentObjectConverterFromId<LinhaPesquisa> linhaPesquisaConverter;
	
	private List<LinhaPesquisa> linhasPesquisa;
	
	
	public ManageLinhasPesquisaController() {
		viewPath = "/manageLinhasPesquisa/";
		bundleName = "msgs";
	}

	@Override
	protected LinhaPesquisa createNewEntity() {
		// TODO Auto-generated method stub
		return new LinhaPesquisa();
	}

	@Override
	protected CrudService<LinhaPesquisa> getCrudService() {
		// TODO Auto-generated method stub
		return manageLinhasPesquisaService;
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		addFilter(new SimpleFilter("manageLinhasPesquisa.filter.byTitulo", "titulo", 
				getI18nMessage("msgs", "manageLinhasPesquisa.text.filter.byTitulo")));

	}
	
	
	
	public Converter getLinhaPesquisaConverter(){
		if(linhaPesquisaConverter == null) {
			linhaPesquisaConverter = new PersistentObjectConverterFromId<LinhaPesquisa>(linhaPesquisaDAO);
		}
		return linhaPesquisaConverter;
	}
	
	public List<LinhaPesquisa> getLinhasPesquisa(){
		linhasPesquisa = new ArrayList<LinhaPesquisa>(manageLinhasPesquisaService.getLinhasPesquisa());
		return linhasPesquisa;
	}
	
}
