package br.ufes.inf.nemo.labs.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.labs.domain.Pessoa;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface PessoaDAO extends BaseDAO<Pessoa> {

	Pessoa retrieveByNome(String nome);
	
}
