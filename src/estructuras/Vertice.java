package estructuras;

import interfaces.Hashable;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Hashable{
	
	private Hashable dato;
	private List<Arista> refs;
	
	public Vertice(Hashable dato) {
		super();
		this.dato = dato;
		this.refs = new ArrayList<Arista>();
	}

	public Vertice() {
		super();
		this.refs = new ArrayList<Arista>();
	}

	public Object getDato() {
		return dato;
	}

	public void setDato(Hashable dato) {
		this.dato = dato;
	}

	public List<Arista> getAristas() {
		return refs;
	}

	public void setRefs(List<Arista> refs) {
		this.refs = refs;
	}

	public String hashStr() {
		return this.dato.hashStr();
	}
	
}
