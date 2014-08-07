package br.ufes.inf.nemo.labs.application;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;
import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.labs.persistence.LinhaPesquisaDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageLinhasPesquisaServiceBean extends
		CrudServiceBean<LinhaPesquisa> implements ManageLinhasPesquisaService {

	@EJB
	private LinhaPesquisaDAO linhaPesquisaDAO;
	
	private List<LinhaPesquisa> linhasPesquisa;
	
	@Override
	public BaseDAO<LinhaPesquisa> getDAO() {
		// TODO Auto-generated method stub
		return linhaPesquisaDAO;
	}

	@Override
	protected LinhaPesquisa createNewEntity() {
		// TODO Auto-generated method stub
		return new LinhaPesquisa();
	}

	@Override
	public List<LinhaPesquisa> getLinhasPesquisa() {
		// TODO Auto-generated method stub
		if(linhasPesquisa == null){
			linhasPesquisa = new ArrayList<LinhaPesquisa>();
			linhasPesquisa.addAll(linhaPesquisaDAO.retrieveAll());
		}
		return linhasPesquisa;
	}

}
