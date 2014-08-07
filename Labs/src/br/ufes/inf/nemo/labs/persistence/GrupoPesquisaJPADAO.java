package br.ufes.inf.nemo.labs.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufes.inf.nemo.labs.domain.GrupoPesquisa;
import br.ufes.inf.nemo.labs.domain.Pessoa;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class GrupoPesquisaJPADAO extends BaseJPADAO<GrupoPesquisa> implements GrupoPesquisaDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Class<GrupoPesquisa> getDomainClass() {
		// TODO Auto-generated method stub
		return GrupoPesquisa.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

	@Override
	public GrupoPesquisa retrieveByNome(String nome) {
		// TODO Auto-generated method stub
		String jpql = "select gp from GrupoPesquisa gp where gp.nome = :nomeGrupo";
		TypedQuery<GrupoPesquisa> query = entityManager.createQuery(jpql, GrupoPesquisa.class);
		query.setParameter("nomeGrupo", nome);
		List<GrupoPesquisa> grupos = query.getResultList();
		
		if(grupos.size() > 0)
			return grupos.get(0);
		
		return null;
	}
	
	/*
	 		
		String jpql = "select p from Pessoa p where p.nome = :nomePessoa";
		TypedQuery<Pessoa> query = entityManager.createQuery(jpql, Pessoa.class);
		query.setParameter("nomePessoa", nome);
		List<Pessoa> pessoas = query.getResultList();
		
		
		if(pessoas.size() > 0)
			return pessoas.get(0);
		
		return null;
	 */

}
