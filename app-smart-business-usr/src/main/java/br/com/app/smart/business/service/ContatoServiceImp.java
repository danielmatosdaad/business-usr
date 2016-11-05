package br.com.app.smart.business.service;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.appsmartbusiness.persistencia.service.ServiceDAO;
import br.com.app.smart.business.dao.facede.ContatoFacade;
import br.com.app.smart.business.dao.interfaces.IServicoLocalDAO;
import br.com.app.smart.business.dao.interfaces.IServicoRemoteDAO;
import br.com.app.smart.business.exception.InfraEstruturaException;
import br.com.app.smart.business.exception.NegocioException;
import br.com.app.smart.business.model.Contato;
import br.com.app.smart.business.usuario.dto.ContatoDTO;

@Stateless
@Remote(value = { IServicoRemoteDAO.class })
@Local(value = { IServicoLocalDAO.class })
public class ContatoServiceImp implements IServicoRemoteDAO<ContatoDTO>, IServicoLocalDAO<ContatoDTO> {


	@EJB
	private ContatoFacade contatoFacade;

	@Override
	public ContatoDTO adiconar(ContatoDTO dto) throws InfraEstruturaException, NegocioException {
		try {
			return ServiceDAO.adiconar(this.contatoFacade, Contato.class, dto);

		} catch (Exception e) {
			throw new InfraEstruturaException(e);

		}
	}

	@Override
	public List<ContatoDTO> adiconar(List<ContatoDTO> listaDto) throws InfraEstruturaException, NegocioException {
		try {
			return ServiceDAO.adiconar(this.contatoFacade, Contato.class, listaDto);

		} catch (Exception e) {
			throw new InfraEstruturaException(e);

		}
	}

	@Override
	public ContatoDTO alterar(ContatoDTO dto) throws InfraEstruturaException, NegocioException {
		try {
			return ServiceDAO.alterar(this.contatoFacade, Contato.class, dto);

		} catch (Exception e) {
			throw new InfraEstruturaException(e);

		}
	}

	@Override
	public void remover(ContatoDTO dto) throws InfraEstruturaException, NegocioException {
		try {
			ServiceDAO.remover(this.contatoFacade, Contato.class, dto);

		} catch (Exception e) {
			throw new InfraEstruturaException(e);

		}

	}

	@Override
	public void removerPorId(Long id) throws InfraEstruturaException, NegocioException {
	

	}

	@Override
	public List<ContatoDTO> bustarTodos() throws InfraEstruturaException, NegocioException {
		try {
			return ServiceDAO.bustarTodos(this.contatoFacade, ContatoDTO.class);

		} catch (Exception e) {
			throw new InfraEstruturaException(e);

		}
	}

	@Override
	public ContatoDTO bustarPorID(Long id) throws InfraEstruturaException, NegocioException {
		try {
			return ServiceDAO.bustarPorID(this.contatoFacade, ContatoDTO.class,id);

		} catch (Exception e) {
			throw new InfraEstruturaException(e);

		}
	}

	@Override
	public List<ContatoDTO> bustarPorIntervaloID(int[] range) throws InfraEstruturaException, NegocioException {
		try {
			return ServiceDAO.bustarPorIntervaloID(this.contatoFacade, ContatoDTO.class,range);

		} catch (Exception e) {
			throw new InfraEstruturaException(e);

		}
	}

}
