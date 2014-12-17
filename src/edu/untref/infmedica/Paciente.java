package edu.untref.infmedica;

public class Paciente {

	private int id;
	private String apellido;
	private String nombre;
	private String historia;

	public Paciente(int id, String apellido, String nombre) {

		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
	}

	public int getId() {

		return id;
	}

	public String getApellido() {

		return apellido;
	}

	public String getNombre() {

		return nombre;
	}

	public String getHistoria() {

		return historia;
	}

	public void setHistoria(String historia) {

		this.historia = historia;
	}
}
