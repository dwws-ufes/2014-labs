package br.ufes.inf.nemo.labs.application;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import com.hp.hpl.jena.rdf.model.Model;

import br.ufes.inf.nemo.labs.domain.Pessoa;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManagePessoasService extends CrudService<Pessoa> {

	List<Pessoa> getPessoas();	
	
	public Model createRdfPessoa(Pessoa p);
	
	public void downloadRdfPessoa(Pessoa p);

}
