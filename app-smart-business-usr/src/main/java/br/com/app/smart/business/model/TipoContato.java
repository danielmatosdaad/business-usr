package br.com.app.smart.business.model;

import java.io.Serializable;

public enum TipoContato implements Serializable{

	EMAIL(1), TELEFONE_RESIDENCIAL(2), CELULAR(3);

	private Integer value;

	private TipoContato(Integer valor) {

		this.value = valor;
	}

	public Integer getValue() {
		return value;
	}
	
}
