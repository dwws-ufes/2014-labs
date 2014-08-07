package br.ufes.inf.nemo.labs.application;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.labs.domain.GrupoPesquisa;
import br.ufes.inf.nemo.labs.domain.LinhaPesquisa;
import br.ufes.inf.nemo.labs.domain.Pessoa;
import br.ufes.inf.nemo.labs.persistence.GrupoPesquisaDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageGruposPesquisaServiceBean extends
		CrudServiceBean<GrupoPesquisa> implements ManageGruposPesquisaService {
	
	@EJB
	private GrupoPesquisaDAO grupoPesquisaDAO;
	
	@EJB
	private ManagePessoasService managePessoasService;
	
	private List<GrupoPesquisa> gruposPesquisa;

	@Override
	public BaseDAO<GrupoPesquisa> getDAO() {
		// TODO Auto-generated method stub
		return grupoPesquisaDAO;
	}

	@Override
	protected GrupoPesquisa createNewEntity() {
		// TODO Auto-generated method stub
		return new GrupoPesquisa();
	}

	@Override
	public List<GrupoPesquisa> getGruposPesquisa() {
		// TODO Auto-generated method stub
		if(gruposPesquisa == null){
			gruposPesquisa = new ArrayList<GrupoPesquisa>();
			gruposPesquisa.addAll(grupoPesquisaDAO.retrieveAll());
		}
		return gruposPesquisa;
	}

	@Override
	public void createRdfPessoa(GrupoPesquisa gp) {
		// TODO Auto-generated method stub
		
		String foaf = "http://xmlns.com/foaf/0.1/";
		
		Model model = ModelFactory.createDefaultModel();
		
		Resource resource = model.createResource(gp.getLocalUri());
		
		model.setNsPrefix("foaf", foaf);
		
		Property name = model.createProperty(foaf + "name");
		Property topicInterest = model.createProperty(foaf + "topic_interest");
		Property member = model.createProperty(foaf + "member");
		
		model.add(resource, name, gp.getNome());
		
		for(LinhaPesquisa lp : gp.getLinhasPesquisa()){
			model.add(resource, topicInterest, lp.getTitulo());
		}
		
		for(Pessoa p : gp.getMembros()){
			Resource pessoa = model.createResource(p.getLocalUri());
			model.add(resource, member, pessoa);
		}		
		
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="+ gp.getNome() +".rdf");
				
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
	
	/*		
		Resource dblp = model.createResource(p.getUriDBLP());
		Resource grupo = model.createResource(p.getGrupoPesquisa().getLocalUri());
		
		model.setNsPrefix("owl", owl);
		
		Property sameAs = model.createProperty(owl + "sameAs");
		Property member = model.createProperty(foaf + "member");
		Property maker = model.createProperty(foaf + "maker");
		Property topicInterest = model.createProperty(foaf + "topic_interest");
		//property same as
		
		
		model.add(resource, sameAs, dblp);
		model.add(resource, member, grupo); //MUDAR PARA URI LOCAL
		
		for(Artigo a : p.getArtigos()){
			Resource publicacao = model.createResource(a.getUri());
			model.add(resource, maker, publicacao);
		}
		
		for(LinhaPesquisa lp : p.getLinhasPesquisa()){
			model.add(resource, topicInterest, lp.getTitulo());
		}
		
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
	 */

}
