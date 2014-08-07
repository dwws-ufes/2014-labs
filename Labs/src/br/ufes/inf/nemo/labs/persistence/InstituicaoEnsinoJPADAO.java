package br.ufes.inf.nemo.labs.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class InstituicaoEnsinoJPADAO extends BaseJPADAO<InstituicaoEnsino> implements InstituicaoEnsinoDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Class<InstituicaoEnsino> getDomainClass() {
		// TODO Auto-generated method stub
		return InstituicaoEnsino.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

}
