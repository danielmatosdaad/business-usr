package br.com.app.smart.business.dao.facede;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.appsmartbusiness.persistencia.dao.facede.AbstractFacade;
import br.com.app.smart.business.model.Contato;

@Stateless
public class ContatoFacade extends AbstractFacade<Contato> {

	public ContatoFacade() {
		super(Contato.class);
	}

	public ContatoFacade(Class<Contato> entityClass) {
		super(entityClass);
	}
	

	@PersistenceContext(unitName = "persistencia-contexto-usuario")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}


	public List<Contato> buscarTodos() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contato> criteria = cb.createQuery(Contato.class);
		Root<Contato> parametro = criteria.from(Contato.class);
		CriteriaQuery<Contato> todos = criteria.select(parametro);
		TypedQuery<Contato> allQuery = em.createQuery(todos);
		
		List<Contato> resultado = allQuery.getResultList();
		
		System.out.println("Quantidade todos? " + resultado.size());
		return resultado;
	}
	
	
}
