package br.com.app.smart.business.model;

import java.io.Serializable;

public enum StatusSenha implements Serializable{

	ATIVO(1), DESABILITADO(2),EXCLUIDO(3);

	private Integer value;

	private StatusSenha(Integer valor) {

		this.value = valor;
	}

	public Integer getValue() {
		return value;
	}
}
