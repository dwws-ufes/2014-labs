package br.ufes.inf.nemo.labs.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface LinhaPesquisaDAO extends BaseDAO<LinhaPesquisa> {

}
