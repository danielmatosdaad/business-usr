package br.com.app.smart.business.model;

import java.io.Serializable;

public enum StatusUsuario implements Serializable{

	ATIVO(1), DESABILITADO(2),EXCLUIDO(3);

	private Integer value;

	private StatusUsuario(Integer valor) {

		this.value = valor;
	}

	public Integer getValue() {
		return value;
	}
}
