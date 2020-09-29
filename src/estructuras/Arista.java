package estructuras;

public class Arista {
	
	private Vertice destino;
	private int costo;
	
	public Arista() {
		super();
	}

	public boolean equals(Object obj) {
		return (this.destino.equals(((Arista)obj).getDestino()));
	}

	public Arista(Vertice destino, int costo) {
		super();
		this.destino = destino;
		this.costo = costo;
	}
	
	public Arista (Vertice destino){
		super();
		this.destino=destino;
		this.costo = 1;
	}

	public Vertice getDestino() {
		return destino;
	}
	
	public void setDestino(Vertice destino) {
		this.destino = destino;
	}
	
	public int getCosto() {
		return costo;
	}
	
	public void setCosto(int costo) {
		this.costo = costo;
	}
}
