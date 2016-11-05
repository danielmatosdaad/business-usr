package br.com.app.smart.business.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.app.smart.business.dao.interfaces.Entidade;

@Entity(name = "usuario")
@XmlRootElement
@Table(name = "usuario")
public class Usuario implements Entidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 1, max = 20, message = "Tamanho maximo de caracteres sao 20")
	@Pattern(regexp = "[^0-9]*", message = "O nome nao pode conter numeros")
	private String nome;

	@NotNull
	@Size(min = 1, max = 25, message = "Tamanho maximo de caracteres sao 25")
	@Pattern(regexp = "[^0-9]*", message = "O sobrenome nao pode conter numeros")
	private String sobrenome;

	@NotNull
	@Size(min = 1, max = 25, message = "Tamanho maximo de caracteres sao 25")
	private String login;

	@OneToMany
	@JoinColumn(name = "conta_id",referencedColumnName="id")
	private List<Contato> contatos;

	@OneToMany
	@JoinColumn(name = "senha_id",referencedColumnName="id")
	private List<Senha> senhas;

	@Enumerated(value = EnumType.STRING)
	private StatusUsuario statusUsuario;

	private RegistroAuditoria registroAuditoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public List<Senha> getSenhas() {
		return senhas;
	}

	public void setSenhas(List<Senha> senhas) {
		this.senhas = senhas;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public RegistroAuditoria getRegistroAuditoria() {
		return registroAuditoria;
	}

	public void setRegistroAuditoria(RegistroAuditoria registroAuditoria) {
		this.registroAuditoria = registroAuditoria;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
