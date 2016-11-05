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

@Entity(name = "senha")
@XmlRootElement
@Table(name = "senha")
public class Senha implements Entidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id",nullable=true)
	private Usuario usuarios;

	@NotNull
	@NotEmpty
	private String hashSenha;

	private RegistroAuditoria registroAuditoria;

	@Enumerated(value = EnumType.STRING)
	private StatusSenha statusSenha;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHashSenha() {
		return hashSenha;
	}

	public void setHashSenha(String hashSenha) {
		this.hashSenha = hashSenha;
	}

	public StatusSenha getStatusSenha() {
		return statusSenha;
	}

	public void setStatusSenha(StatusSenha statusSenha) {
		this.statusSenha = statusSenha;
	}

	public RegistroAuditoria getRegistroAuditoria() {
		return registroAuditoria;
	}

	public void setRegistroAuditoria(RegistroAuditoria registroAuditoria) {
		this.registroAuditoria = registroAuditoria;
	}

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

}
