package br.ufes.inf.nemo.labs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.labs.application.ManageArtigosService;
import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.sparql.DBLP;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.ListingService;
import br.ufes.inf.nemo.util.ejb3.application.filters.SimpleFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.ListingController;

@Named
@SessionScoped
public class ManageArtigosController extends ListingController<Artigo> {
	
	@EJB
	private ManageArtigosService manageArtigosService;
	
	
	
	public ManageArtigosController(){
		viewPath = "/manageArtigos/";
		bundleName = "msgs";
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		addFilter(new SimpleFilter("manageArtigos.filter.byTitulo", "titulo", 
				getI18nMessage("msgs", "manageArtigos.text.filter.byTitulo")));
	}

	@Override
	protected ListingService<Artigo> getListingService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 public List<LinhaPesquisa> getLinhasPesquisa(){
		linhasPesquisa = new ArrayList<LinhaPesquisa>(manageLinhasPesquisaService.getLinhasPesquisa());
		return linhasPesquisa;
	}
	 */

}
