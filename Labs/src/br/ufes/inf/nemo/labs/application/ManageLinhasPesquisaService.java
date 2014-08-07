package br.ufes.inf.nemo.labs.application;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageLinhasPesquisaService extends CrudService<LinhaPesquisa> {

	List<LinhaPesquisa> getLinhasPesquisa();

}
