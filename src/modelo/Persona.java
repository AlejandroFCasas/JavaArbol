package modelo;

import interfaces.Hashable;

public class Persona implements Hashable {
	
	private String nombre;
	private int dni;

	
	public Persona() {
		super();
	}

	public Persona(String nombre, int dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String hashStr() {
		return this.nombre;
	}

	public String toString() {
		return "Nombre: "+this.getNombre()+" DNI: "+this.getDni();
	}
}
