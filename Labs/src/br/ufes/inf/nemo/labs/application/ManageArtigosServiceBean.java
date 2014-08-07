package br.ufes.inf.nemo.labs.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.labs.persistence.ArtigoDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageArtigosServiceBean extends CrudServiceBean<Artigo> implements ManageArtigosService {

	@EJB
	private ArtigoDAO artigoDAO;
	
	@Override
	public BaseDAO<Artigo> getDAO() {
		// TODO Auto-generated method stub
		return artigoDAO;
	}

	@Override
	protected Artigo createNewEntity() {
		// TODO Auto-generated method stub
		return new Artigo();
	}

}
