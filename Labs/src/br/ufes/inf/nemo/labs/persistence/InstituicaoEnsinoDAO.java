package br.ufes.inf.nemo.labs.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface InstituicaoEnsinoDAO extends BaseDAO<InstituicaoEnsino> {

}
