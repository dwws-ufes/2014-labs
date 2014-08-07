package br.ufes.inf.nemo.labs.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-04T16:54:50.532-0300")
@StaticMetamodel(GrupoPesquisa.class)
public class GrupoPesquisa_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<GrupoPesquisa, String> nome;
	public static volatile SingularAttribute<GrupoPesquisa, String> sigla;
	public static volatile SingularAttribute<GrupoPesquisa, String> localUri;
	public static volatile SingularAttribute<GrupoPesquisa, Integer> anoFundacao;
	public static volatile SetAttribute<GrupoPesquisa, LinhaPesquisa> linhasPesquisa;
	public static volatile SingularAttribute<GrupoPesquisa, InstituicaoEnsino> instituicaoEnsino;
	public static volatile SetAttribute<GrupoPesquisa, Pessoa> membros;
}
