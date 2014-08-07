package br.ufes.inf.nemo.labs.application;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.assembler.Mode;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.labs.domain.Pessoa;
import br.ufes.inf.nemo.labs.persistence.PessoaDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManagePessoasServiceBean extends CrudServiceBean<Pessoa>
		implements ManagePessoasService {

	@EJB
	private PessoaDAO pessoaDAO;
	
	private List<Pessoa> pessoas;
	
	@Override
	public BaseDAO<Pessoa> getDAO() {
		// TODO Auto-generated method stub
		return pessoaDAO;
	}

	@Override
	protected Pessoa createNewEntity() {
		// TODO Auto-generated method stub
		return new Pessoa();
	}

	@Override
	public List<Pessoa> getPessoas() {
		// TODO Auto-generated method stub
		if(pessoas == null) {
			pessoas = new ArrayList<Pessoa>();
			pessoas.addAll(pessoaDAO.retrieveAll());
		}
		return pessoas;
	}
	
	public String createUriLocal(String nome){
		
		if(nome != null){
			nome = nome.trim();
			nome = nome.replace(" ", "_");
			nome = URLEncoder.encode(nome);
			nome = "http://localhost:8080/Labs/pessoa/" + nome;
			return nome;
		}
		return null;
	}
	
	public Model createRdfPessoa(Pessoa p){
		
		String foaf = "http://xmlns.com/foaf/0.1/";
		String owl = "http://w3.org/2002/07/owl#";
		
		Model model = ModelFactory.createDefaultModel();
		
		Resource resource = model.createResource(p.getLocalUri());
		Resource dblp = model.createResource(p.getUriDBLP());
		Resource grupo = model.createResource(p.getGrupoPesquisa().getLocalUri());
		
		model.setNsPrefix("foaf", foaf);
		model.setNsPrefix("owl", owl);
		
		Property sameAs = model.createProperty(owl + "sameAs");
		Property name = model.createProperty(foaf + "name");
		Property member = model.createProperty(foaf + "member");
		Property maker = model.createProperty(foaf + "maker");
		Property topicInterest = model.createProperty(foaf + "topic_interest");
		//property same as
		
		
		model.add(resource, sameAs, dblp);
		model.add(resource, name, p.getNome());
		model.add(resource, member, grupo); //MUDAR PARA URI LOCAL
		
		for(Artigo a : p.getArtigos()){
			Resource publicacao = model.createResource(a.getUri());
			model.add(resource, maker, publicacao);
		}
		
		for(LinhaPesquisa lp : p.getLinhasPesquisa()){
			model.add(resource, topicInterest, lp.getTitulo());
		}
		
		return model;
	}
	
	public void downloadRdfPessoa(Pessoa p){
		
		Model model = createRdfPessoa(p);
		
		//model.write(System.out, "RDF/XML-ABBREV");
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="+ p.getNome() +".rdf");
				
		try {
			OutputStream responseOutputStream = response.getOutputStream();
			
			model.write(responseOutputStream, "RDF/XML-ABBREV");
						
			responseOutputStream.flush();
			responseOutputStream.close();
			facesContext.responseComplete();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}
