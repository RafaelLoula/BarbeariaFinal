package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Horario {
	
	@Id
	@GeneratedValue
	private int id;
	private String horas;
	
	public Horario() {
		// TODO Auto-generated constructor stub
	}
	
	public Horario(String horas, int id) {
		this.setHorario(horas);
		this.setId(id);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Horario(String hora) {
		this.setHorario(hora);
	}
	
	public String getHorario() {
		return horas;
	}

	public void setHorario(String horario) {
		this.horas = horario;
	}

	@Override
	public String toString() {
		return getHorario();
	}

	
	

}
