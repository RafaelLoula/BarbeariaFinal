package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Agendamento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String cliente;
	private String data;
	private String barbeiro;
	private String corte;
	private String hora;
	@Transient
	private String descricao;
	
	public Agendamento() {
	}
	
	

	public Agendamento(String cliente, String data, String barbeiro, String corte, String hora) {
		this.setCliente(cliente);
		this.setData(data);
		this.setBarbeiro(barbeiro);
		this.setCorte(corte);
		this.setHora(hora);
	}

	public String getCliente() {
		return cliente;
	}



	public void setCliente(String cliente) {
		this.cliente = cliente;
	}



	public String getBarbeiro() {
		return barbeiro;
	}



	public void setBarbeiro(String barbeiro) {
		this.barbeiro = barbeiro;
	}



	public String getCorte() {
		return corte;
	}



	public void setCorte(String corte) {
		this.corte = corte;
	}



	public String getHora() {
		return hora;
	}



	public void setHora(String hora) {
		this.hora = hora;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public String getData() {
		return data;
	}



	public void setData(String data) {	
		this.data = data;
	}



	@Override
	public String toString() {
		return "Agendamento [id=" + id + ", cliente=" + cliente + ", data=" + data + ", barbeiro=" + barbeiro
				+ ", corte=" + corte + ", hora=" + hora + ", descricao=" + descricao + "]";
	}



	
	
	

	




	
	

}
