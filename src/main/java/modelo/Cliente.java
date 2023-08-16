package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 String nome;
	 String endereco;
	 String celular;
	

	public Cliente(int id) {
		super();
		this.id = id;
	}
	
	public Cliente(String nome, String Endereco, String Celular) {
		this.setNome(nome);
		this.setEndereco(Endereco);
		this.setCelular(Celular);
	}

	public Cliente() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
	
	

}
