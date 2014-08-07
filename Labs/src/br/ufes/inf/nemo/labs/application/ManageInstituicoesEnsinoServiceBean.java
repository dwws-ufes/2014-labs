package br.ufes.inf.nemo.labs.application;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;
import br.ufes.inf.nemo.labs.persistence.InstituicaoEnsinoDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageInstituicoesEnsinoServiceBean extends CrudServiceBean<InstituicaoEnsino> implements ManageInstituicoesEnsinoService {
	

	@EJB
	private InstituicaoEnsinoDAO instituicaoEnsinoDAO;
	
	private List<InstituicaoEnsino> instituicoesEnsino;
	
	@Override
	public BaseDAO<InstituicaoEnsino> getDAO() {
		// TODO Auto-generated method stub
		return instituicaoEnsinoDAO;
	}

	@Override
	protected InstituicaoEnsino createNewEntity() {
		// TODO Auto-generated method stub
		return new InstituicaoEnsino();
	}
	
	
	@Override
	public List<InstituicaoEnsino> getInstituicoesEnsino(){
		if(instituicoesEnsino == null){
			instituicoesEnsino = new ArrayList<InstituicaoEnsino>();
			instituicoesEnsino.addAll(instituicaoEnsinoDAO.retrieveAll());
		}
		return instituicoesEnsino;
	}
		

}
