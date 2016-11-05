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
import br.com.app.smart.business.model.Usuario;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

	public UsuarioFacade() {
		super(Usuario.class);
	}

	public UsuarioFacade(Class<Usuario> entityClass) {
		super(entityClass);
	}
	

	@PersistenceContext(unitName = "persistencia-contexto-usuario")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}


	public List<Usuario> buscarTodos() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> parametro = criteria.from(Usuario.class);
		CriteriaQuery<Usuario> todos = criteria.select(parametro);
		TypedQuery<Usuario> allQuery = em.createQuery(todos);
		
		List<Usuario> resultado = allQuery.getResultList();
		
		System.out.println("Quantidade todos? " + resultado.size());
		return resultado;
	}
}
