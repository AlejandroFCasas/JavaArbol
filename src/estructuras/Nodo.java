package estructuras;

public class Nodo {
	
	private Object dato;
	private Nodo siguiente;
	
	public Nodo() {
		super();
	}
	public Nodo(Object x) {
		this.dato = x;
	}
	public Object getDato() {
		return dato;
	}
	public void setDato(Object dato) {
		this.dato = dato;
	}
	public Nodo getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo siguiente) {
		this.siguiente = siguiente;
	}
	
}
