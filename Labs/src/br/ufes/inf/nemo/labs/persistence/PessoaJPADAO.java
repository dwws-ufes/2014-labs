package br.ufes.inf.nemo.labs.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufes.inf.nemo.labs.domain.Pessoa;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class PessoaJPADAO extends BaseJPADAO<Pessoa> implements PessoaDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Class<Pessoa> getDomainClass() {
		// TODO Auto-generated method stub
		return Pessoa.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

	@Override
	public Pessoa retrieveByNome(String nome) {
		// TODO Auto-generated method stub
		
		String jpql = "select p from Pessoa p where p.nome = :nomePessoa";
		TypedQuery<Pessoa> query = entityManager.createQuery(jpql, Pessoa.class);
		query.setParameter("nomePessoa", nome);
		List<Pessoa> pessoas = query.getResultList();
		
		
		if(pessoas.size() > 0)
			return pessoas.get(0);
		
		return null;
	}

}
