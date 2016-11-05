package br.com.app.smart.business.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.app.smart.business.dao.interfaces.Entidade;

@Entity
@XmlRootElement
@Table(name = "contato")
public class Contato implements Entidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id",nullable=true)
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	private TipoContato tipoContato;

	@NotNull
	@NotEmpty
	private String valor;

	private RegistroAuditoria registroAuditoria;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public RegistroAuditoria getRegistroAuditoria() {
		return registroAuditoria;
	}

	public void setRegistroAuditoria(RegistroAuditoria registroAuditoria) {
		this.registroAuditoria = registroAuditoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	


}
