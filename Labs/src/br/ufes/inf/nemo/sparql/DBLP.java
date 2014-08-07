package br.ufes.inf.nemo.sparql;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.text.StyledEditorKit.ItalicAction;

import br.ufes.inf.nemo.labs.domain.Artigo;
import br.ufes.inf.nemo.labs.domain.Pessoa;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class DBLP {
	//http://dblp.l3s.de/d2r/sparql
	
	private static String ENDPOINT = "http://dblp.l3s.de/d2r/sparql";

	public static List<Artigo> getArtigos(String uriPessoa, Integer ano) {
		// TODO Auto-generated method stub
		long ti = System.currentTimeMillis();
		/*String query = " PREFIX dc: <http://purl.org/dc/elements/1.1/> "
				+ " PREFIX dcterms: <http://purl.org/dc/terms/> "
				+ " PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ " 	SELECT ?artigo ?title ?anoPublicacao"
				+ " WHERE "
				+ " { "
				+ " ?artigo  dc:creator <" + uriPessoa + ">."
				+ " ?artigo dc:title ?title."
				+ " ?artigo dcterms:issued ?anoPublicacao"
				+ " }"
				+ "ORDER BY DESC (?anoPublicacao) ?title";*/
		
		String query = " PREFIX dc: <http://purl.org/dc/elements/1.1/> "
				+ " PREFIX dcterms: <http://purl.org/dc/terms/> "
				+ " PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ " 	SELECT ?artigo ?title ?anoPublicacao ?homepage"
				+ " WHERE "
				+ " { {"
				+ " ?artigo  dc:creator <" + uriPessoa + ">."
				+ " ?artigo dc:title ?title."
				+ " ?artigo dcterms:issued ?anoPublicacao."
				+ " FILTER (?anoPublicacao >= " + ano + ")"
				+ "	}UNION{"
				+ " ?artigo  dc:creator <" + uriPessoa + ">."
				+ " ?artigo dc:title ?title."
				+ " ?artigo dcterms:issued ?anoPublicacao."	
				+ "	?artigo foaf:homepage ?homepage."
				+ " FILTER (?anoPublicacao >= " + ano + ")"
				+ " }}"
				+ "ORDER BY DESC (?anoPublicacao) ASC (?title) DESC (?homepage)";
		
		ResultSet results = SPARQLUtil.externalQuery(query, ENDPOINT);
	
		
		List<Artigo> artigos = new ArrayList<Artigo>();
		
		
		while(results.hasNext()){
			
			QuerySolution soln = results.next();
			Resource recursoArtigo = soln.getResource("artigo");
			
			if(artigos.size() == 0 || !artigos.get(artigos.size()-1).getUri().equals(recursoArtigo.getURI())){
			
				Literal title = soln.getLiteral("title");
				Literal anoPublicacao = soln.getLiteral("anoPublicacao");
			
				Artigo artigo = new Artigo();
				artigo.setTitulo(title.getString());
				artigo.setUri(recursoArtigo.getURI());
				if(soln.getResource("homepage") != null){
					artigo.setLink(soln.getResource("homepage").getURI());
				}
				artigo.setAnoPublicacao(Integer.parseInt(anoPublicacao.getString()));
			
				artigos.add(artigo);
			}
			
		}
		long tf = System.currentTimeMillis();
		System.out.println("\nTempo: " + ((tf-ti)/1000) + " segundos" );
		return artigos;
	}
	
	
	public static List<Artigo> getArtigos(Set<Pessoa> membros, Integer ano) {
		// TODO Auto-generated method stub
		long ti = System.currentTimeMillis();
		
		
		
		Iterator<Pessoa> it = membros.iterator();
		
		List<Artigo> artigos = new ArrayList<Artigo>();
		
		while(it.hasNext()){
			Pessoa p = (Pessoa) it.next();
			artigos.addAll(getArtigos(p.getUriDBLP(), ano));
		}
		
		Collections.sort(artigos, new Comparator<Artigo>() {

			@Override
			public int compare(Artigo o1, Artigo o2) {
				// TODO Auto-generated method stub
				
				return o1.compareTo(o2);
			}
			
		});
		
		long tf = System.currentTimeMillis();
		System.out.println("\nTempo: " + ((tf-ti)/1000) + " segundos" );
		
		
		List<Artigo> novoArtigos = new ArrayList<Artigo>();
		
		if(artigos.size() > 0){
			novoArtigos.add(artigos.get(0));
		}
		
		for(int i=1; i < artigos.size(); i++ ){
			if(!novoArtigos.get(novoArtigos.size()-1).getUri().equals(artigos.get(i).getUri())){
				novoArtigos.add(artigos.get(i));
			}
		}
		
		return novoArtigos;
	}
	

	public static String getLinkArtigo(String uriArtigo){
		
		String query = " PREFIX dc: <http://purl.org/dc/elements/1.1/> "
				+ " PREFIX dcterms: <http://purl.org/dc/terms/> "
				+ " PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ " 	SELECT ?link"
				+ " WHERE "
				+ " { "
				+ "<" + uriArtigo + "> foaf:homepage ?link"
				+ " }";
		
		ResultSet results = SPARQLUtil.externalQuery(query, ENDPOINT);
		
		String link = null;
		
		while(results.hasNext()){
			QuerySolution soln = results.next();
			Resource homepage = soln.getResource("link");
			link = homepage.getURI();
			return link;
		}
		
		return link;		
	}
	
}
