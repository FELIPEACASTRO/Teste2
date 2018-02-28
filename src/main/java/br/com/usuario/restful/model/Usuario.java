package br.com.usuario.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
public class Usuario implements java.io.Serializable  {
	
	
	private static final long serialVersionUID = 4910225916550731446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "nomecompleto", length = 100)
	private String nomecompleto;
	
	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "datanascimento" , length = 10)
	private String datanascimento;

	@Column(name = "timecoracao", length = 50)
	private String timecoracao;
	
	
	public Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}
	
	public Usuario(Long id, String nomecompleto, String email, String datanascimento,String timecoracao) {
		this.id = id;
		this.nomecompleto = nomecompleto;
		this.email = email;
		this.datanascimento = datanascimento;
		this.timecoracao = timecoracao;
	}

	public Usuario(String nomecompleto, String email, String datanascimento,String timecoracao) {
		this.nomecompleto = nomecompleto;
		this.email = email;
		this.datanascimento = datanascimento;
		this.timecoracao = timecoracao;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomecompleto() {
		return nomecompleto;
	}

	public void setNomecompleto(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getTimecoracao() {
		return timecoracao;
	}

	public void setTimecoracao(String timecoracao) {
		this.timecoracao = timecoracao;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Id: ").append(this.id).append(", NomeCompleto: ").append(this.nomecompleto).append(", Email: ")
				.append(this.email).append(", DataNascimento: ").append(this.datanascimento).append(", TimeCoracao: ").append(this.timecoracao);
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (id == null || obj == null || getClass() != obj.getClass())
			return false;
		Usuario toCompare = (Usuario) obj;
		return id.equals(toCompare.id);
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

}
