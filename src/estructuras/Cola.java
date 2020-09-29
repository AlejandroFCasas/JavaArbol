package estructuras;

public class Cola implements interfaces.Cola {

	Nodo ini = new Nodo();
	Nodo fin = new Nodo();
	
	public void agregar(Object x) {
		Nodo n = new Nodo(x);
		if (this.esVacia()){
			fin.setSiguiente(n);
			ini.setSiguiente(n);
		}else{
			fin.getSiguiente().setSiguiente(n);
			fin.setSiguiente(n);
		}
	}
	
	public boolean esVacia() {
		return ini.getSiguiente() == null;
		/* if (ini.getSiguiente() == null){
			return true;
		}
		return false;*/
	}
	
	public void mostrar() {
	if(this.esVacia()){
		return;
	}
	Nodo temp = ini.getSiguiente();
	while (temp != null){
		System.out.println(temp.getDato());
		temp = (temp.getSiguiente());
	}

	}

	
	public Object primero() {
		if (this.esVacia()){
			System.err.println("La cola esta vacia...");
			return null;
		}
		return ini.getSiguiente().getDato();
	}

	
	public Object qprimero() {
		if (this.esVacia()){
			System.err.println("No hay elementos que quitar, la cola esta vacia");
			return null;
		}
		Object temp = ini.getSiguiente().getDato();
		ini.setSiguiente(ini.getSiguiente().getSiguiente());
		if(ini.getSiguiente()==null) fin.setSiguiente(null);
		return temp;
	}

	
	public void vaciar() {
	ini.setSiguiente(null);
	}

}
