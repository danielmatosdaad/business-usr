package br.com.app.smart.business.databuilder;

import java.util.Date;
import br.com.app.smart.business.dto.RegistroAuditoriaDTO;
import br.com.app.smart.business.usuario.dto.SenhaDTO;
import br.com.app.smart.business.usuario.dto.StatusSenhaDTO;

public class SenhaBuilder {

	public static SenhaDTO getInstanceDTO(TipoSenhaBuilder tipo) {

		switch (tipo) {

		case SENHA_ACESSO:
			return criarSenhaAcesso();

		default:
			break;
		}
		return new SenhaDTO();
	}

	private static SenhaDTO criarSenhaAcesso() {
		RegistroAuditoriaDTO r = new RegistroAuditoriaDTO();
		r.setDataCadastro(new Date());

		SenhaDTO senhaDTO = new SenhaDTO();
		senhaDTO.setHashSenha("=3DF34334DFDDF");
		senhaDTO.setStatusSenha(StatusSenhaDTO.ATIVO);
		senhaDTO.setRegistroAuditoria(r);

		return senhaDTO;

	}

	public static enum TipoSenhaBuilder {

		INSTANCIA,SENHA_ACESSO;
	}
}
