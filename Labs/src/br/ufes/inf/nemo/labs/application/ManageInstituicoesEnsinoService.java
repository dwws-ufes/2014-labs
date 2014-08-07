package br.ufes.inf.nemo.labs.application;

import java.util.List;
import java.util.SortedSet;

import javax.ejb.Local;

import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageInstituicoesEnsinoService extends CrudService<InstituicaoEnsino> {
	
	public List<InstituicaoEnsino> getInstituicoesEnsino();

}
