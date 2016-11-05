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
import br.com.app.smart.business.model.Senha;

@Stateless
public class SenhaFacade extends AbstractFacade<Senha> {

	public SenhaFacade() {
		super(Senha.class);
	}

	public SenhaFacade(Class<Senha> entityClass) {
		super(entityClass);
	}
	

	@PersistenceContext(unitName = "persistencia-contexto-usuario")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}


	public List<Senha> buscarTodos() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Senha> criteria = cb.createQuery(Senha.class);
		Root<Senha> parametro = criteria.from(Senha.class);
		CriteriaQuery<Senha> todos = criteria.select(parametro);
		TypedQuery<Senha> allQuery = em.createQuery(todos);
		
		List<Senha> resultado = allQuery.getResultList();
		
		System.out.println("Quantidade todos? " + resultado.size());
		return resultado;
	}
}
