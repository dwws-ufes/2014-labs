package br.ufes.inf.nemo.labs.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.labs.domain.GrupoPesquisa;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface GrupoPesquisaDAO extends BaseDAO<GrupoPesquisa> {

	GrupoPesquisa retrieveByNome(String nome);
	
}
