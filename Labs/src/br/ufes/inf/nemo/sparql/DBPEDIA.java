package br.ufes.inf.nemo.sparql;

import br.ufes.inf.nemo.labs.domain.InstituicaoEnsino;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;

public class DBPEDIA {
	
	private static String ENDPOINT = "http://pt.dbpedia.org/sparql";

	public static void main(String[] args) {

		String query1 = " 	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ " PREFIX : <http://dbpedia.org/resource/>"
				+ " PREFIX d: <http://dbpedia.org/ontology/> "
				+ " SELECT distinct ?albumName ?artistName "
				+ " WHERE "
				+ " { "
				+ " ?album d:producer :Timbaland . "
				+ " ?album d:musicalArtist ?artist ."
				+ " ?album rdfs:label ?albumName . "
				+ " ?artist rdfs:label ?artistName ."
				+ " FILTER ( lang(?artistName) = \"en\")"
				+ " FILTER ( lang(?albumName) = \"en\" )" + " }";

		ResultSet results = SPARQLUtil.dbpediaQuery(query1);
		ResultSetFormatter.out(System.out, results);

		while (results.hasNext()) {

			QuerySolution soln = results.next();

			Literal albumName = soln.getLiteral("albumName");
			Literal artistName = soln.getLiteral("artistName");
			
			System.out.println(albumName+"--"+artistName);

		}

	}
	
	public static InstituicaoEnsino getInformacoes(InstituicaoEnsino instituicaoEnsino){
		
		String query = "PREFIX :<http://pt.dbpedia.org>"
					+  " PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
					+  " PREFIX d: <http://pt.dbpedia.org/property/>"
					+  " select  ?homepage ?nameSede ?reitor"
					+  " where {"
					+  " <" + instituicaoEnsino.getUri() + "> foaf:homepage ?homepage."
					+  " <" + instituicaoEnsino.getUri() + "> d:sede ?sede."
					+  " <" + instituicaoEnsino.getUri() + "> d:reitor ?reitor."
					+  " ?sede foaf:name ?nameSede"
					+  " }";
		ResultSet results = SPARQLUtil.externalQuery(query, ENDPOINT);
		
		while (results.hasNext()) {
			QuerySolution soln = results.next();
			instituicaoEnsino.setSite(soln.getResource("homepage").getURI());
			instituicaoEnsino.setSede(soln.getLiteral("nameSede").getString());
			instituicaoEnsino.setReitor(soln.getLiteral("reitor").getString());
		}
		
		return instituicaoEnsino;
	}
	
	/*
	 *PREFIX :http://pt.dbpedia.org
	 PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX d: <http://pt.dbpedia.org/property/>
select  ?hpage ?nameSede ?reitor
where { 
 <http://pt.dbpedia.org/resource/Universidade_Federal_do_Espírito_Santo> foaf:homepage ?hpage.
 <http://pt.dbpedia.org/resource/Universidade_Federal_do_Espírito_Santo> d:sede ?sede.
 <http://pt.dbpedia.org/resource/Universidade_Federal_do_Espírito_Santo> d:reitor ?reitor.
 ?sede foaf:name ?nameSede
} 
	 */
	
}