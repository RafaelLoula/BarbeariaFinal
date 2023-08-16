package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Corte {
	
	@Id
	@GeneratedValue
	private int id;
	private String nomeCorte;
	private double valor;
	
	public Corte() {
	}
	
	public Corte(String nomeCorte, double valor) {
		this.setNomeCorte(nomeCorte);
		this.setValor(valor);
	}
	
	public String getNomeCorte() {
		return nomeCorte;
	}
	public void setNomeCorte(String nomeCorte) {
		this.nomeCorte = nomeCorte;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return getNomeCorte();
	}
	
	
	

}
