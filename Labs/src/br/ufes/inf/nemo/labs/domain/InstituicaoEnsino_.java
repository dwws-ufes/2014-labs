package br.ufes.inf.nemo.labs.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-21T13:59:02.970-0300")
@StaticMetamodel(InstituicaoEnsino.class)
public class InstituicaoEnsino_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<InstituicaoEnsino, String> nome;
	public static volatile SingularAttribute<InstituicaoEnsino, String> sigla;
	public static volatile SingularAttribute<InstituicaoEnsino, String> uri;
	public static volatile SetAttribute<InstituicaoEnsino, GrupoPesquisa> gruposPesquisa;
}
