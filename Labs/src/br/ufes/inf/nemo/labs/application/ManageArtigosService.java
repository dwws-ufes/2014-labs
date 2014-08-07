package br.ufes.inf.nemo.labs.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageArtigosService extends CrudService<Artigo> {

}
