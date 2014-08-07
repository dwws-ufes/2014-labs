package br.ufes.inf.nemo.labs.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class LinhaPesquisaJPADAO extends BaseJPADAO<LinhaPesquisa> implements LinhaPesquisaDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Class<LinhaPesquisa> getDomainClass() {
		// TODO Auto-generated method stub
		return LinhaPesquisa.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

}
