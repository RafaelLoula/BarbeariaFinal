package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Barbeiro extends Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String login;
	String senha;
	String nome;
	
	public Barbeiro() {
		}	
	
	public Barbeiro( String login, String senha) {
		super();
		this.setNome(login);
		this.setLogin(login);
		this.setSenha(senha);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return getLogin();
	}
	
	
	
	
}
