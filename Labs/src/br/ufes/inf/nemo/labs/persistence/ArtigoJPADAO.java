package br.ufes.inf.nemo.labs.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class ArtigoJPADAO extends BaseJPADAO<Artigo> implements ArtigoDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Class<Artigo> getDomainClass() {
		// TODO Auto-generated method stub
		return Artigo.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

}
