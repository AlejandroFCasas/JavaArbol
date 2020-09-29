package estructuras;
import interfaces.Hashable;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
public class Grafo {

	private int numVertices = 0;
	private Hashtable<String,Vertice> vertices;
	private int[][] path;
	private int [][] interm;
	private int [] distanciasOrigen;
	private int [] verticesAnt;
	private ArrayList<Vertice> vert;
	
	
	private static final int INFI = 2147483647/3;
	private static final int VERT_NULO = -1;
	
	public Grafo (){
		numVertices = 0;
		vertices = new Hashtable<String,Vertice>();
	}
	
	public int getNumVertices() {
		return numVertices;
	}

	public boolean insVertice(Hashable dato){
		Vertice v= new Vertice(dato);
		
		Vertice result = vertices.put(v.hashStr(),v);
		if (result == null){
			v.getAristas().add(new Arista(v,0));
			numVertices++;
			return true;
		}else
			return false;
	}
	
	public boolean insArista (String origen, String destino, int costo){
		Vertice v = vertices.get(origen);
		Vertice w = vertices.get(destino);
		if (v != null && w != null){
			Arista a = new Arista (w,costo);
			if (v.getAristas().contains(a)) return false;
			v.getAristas().add(a);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void imprimirGrafo(){
		Enumeration<Vertice> e= vertices.elements();
		Vertice tmp;
		Arista arista;
		while(e.hasMoreElements()){
			tmp = e.nextElement();
			
			//hace algo con tmp
			System.out.println("Vertice: "+ tmp.getDato().toString());
			for(int i=0;i < tmp.getAristas().size();i++){
				arista = tmp.getAristas().get(i);
				System.out.println("\t Arista al vertice "+arista.getDestino().hashStr()+" con costo "+(arista.getCosto()<INFI?arista.getCosto():"INF"));
			}
			System.out.println("");
		}
	}
	
	// Obtiene el vector de caminos con menos ARISTAS
	public void bfs (String origen){
		int n = numVertices, indv,indw;
		
		Vertice v = vertices.get(origen);
		Vertice w;
		if (v==null){
			System.out.println("No existe el origen.");
			return;
		}
		
		Enumeration<Vertice> e= vertices.elements();
		vert = new ArrayList<Vertice>();
		for(int i=0;e.hasMoreElements();i++){
			vert.add(e.nextElement());
		}
		
		Cola c = new Cola();
		distanciasOrigen = new int [n];
		for (int i=0;i<numVertices;i++) distanciasOrigen[i]=INFI;
		verticesAnt = new int [n];
		for (int i=0;i<numVertices;i++) verticesAnt[i]=VERT_NULO;
		
		distanciasOrigen[vert.indexOf(v)] = 0;
		c.agregar(v);
		
		while (!c.esVacia()){
			v = (Vertice)c.qprimero();
			indv = vert.indexOf(v);
			
			for(int i=0;i < v.getAristas().size();i++){
				w = v.getAristas().get(i).getDestino();
				indw = vert.indexOf(w);
				if(distanciasOrigen[indw] >= INFI){
					distanciasOrigen[indw]=distanciasOrigen[indv]+1;
					verticesAnt[indw]=indv;
					c.agregar(w);
				}
			}
		}	
	}

	//muestra la distancia(sin costo) CONTANDO ARISTAS desde cada vert al vertice seleccionado como origen
	public void mostrarDistOrigen(String origen){
		this.bfs(origen);
		System.out.println("Desde el origen: "+origen);
		for(int i=0; i<numVertices; i++)
			System.out.println("\t La distancia a "+vert.get(i).hashStr()+" es "+distanciasOrigen[i]);
		System.out.println("");
		vert.clear();
	}

	//muestra EL CAMINO con menos aristas entre dos vertices
	//MODIFICAR SI: pide devolver el camino CON MENOS ARISTAS en listas
	public void mostrarCaminoMin2Vert(String origen, String destino){
		this.bfs(origen);
		String s="";
		
		int indw = vert.indexOf(vertices.get(destino));
		if(indw==-1){
			System.out.println("No existe el vertice destino");
			return;
		}
		while (verticesAnt[indw]!=VERT_NULO){
			s=(vert.get(indw).hashStr()+" ")+s;
			indw = verticesAnt[indw];
		}
		s =(vert.get(indw).hashStr()+" ")+s;
		if(distanciasOrigen[indw]!=0)
			System.out.println("Camino no existente entre "+origen+" y "+destino);
		else
			System.out.println("Camino minimo: "+s);
	}
	
	
	
	//crea la tabla de costo minimos CON PESOS positivos y negativos
	public void floydWarshall (){
		int n = numVertices;
		
		//obtiene una lista de los vertices
		Enumeration<Vertice> e= vertices.elements();
		vert = new ArrayList<Vertice>();
		for(int i=0;e.hasMoreElements();i++){
			vert.add(e.nextElement());
		}
		
		//arma matriz de adyacencia
		path = new int[n][n];
		interm = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++){
				int ind = vert.get(i).getAristas().indexOf(new Arista(vert.get(j)));
				if (ind>=0)
					path[i][j]= vert.get(i).getAristas().get(ind).getCosto();
				else
					path[i][j]= INFI;
				interm[i][j] = VERT_NULO;
			}
				
		//floyd
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (path [i][j]> path [i][k]+path [k][j]){
						path[i][j]= path [i][k]+path [k][j];
						interm[i][j]= k;
					}
	}
	
	// muestra EL CAMINO minimo(considerando costo) entre todos los vertices
	// MODIFICAR SI: pide devolver lista con algunos caminos minimos
	public void mostrarCaminosMinConCostos(){
		this.floydWarshall();
		
		//control de ciclos negativos
		for (int i = 0; i < numVertices; i++){
			if (path[i][i]<0){
				System.out.println("No se puede calcular caminos minimos, por que contiene ciclos negativos");
				return;
			}
		}
		
		System.out.println("CAMINOS MINIMOS CON COSTOS");
		for (int i=0 ;i < numVertices; i++)
			for (int j=0; j < numVertices ; j++)
				System.out.println("   "+vert.get(i).hashStr()+mostrarCaminoMinConCostos(i,j)+vert.get(j).hashStr());
		System.out.println("");

	}
	//parte del de arriba
	private String mostrarCaminoMinConCostos(int i, int j) {
		if(path[i][j] >= INFI) return("No hay camino entre "+vert.get(i).hashStr()+" y "+vert.get(j).hashStr());
		int k = interm[i][j];
		if(k ==VERT_NULO) return (" ");
		else
			return (mostrarCaminoMinConCostos(i,k)+" "+vert.get(k).hashStr()+" "+mostrarCaminoMinConCostos(k,j));
	}
	
	// muestra el costo del camino minimo entre todos los pares de vertices
	public void mostrarCaminosMinimos(){
		this.floydWarshall();
		
		//control de ciclos negativos
		for (int i = 0; i < numVertices; i++){
			if (path[i][i]<0){
				System.out.println("No se puede calcular caminos minimos, por que contiene ciclos negativos");
				return;
			}
		}
		
		//imprimir matriz
		System.out.println("Matriz Floyd-Warshall");
		for (int i = 0; i < numVertices; i++){
			for (int j = 0; j < numVertices; j++){
				System.out.print("\t"+(path[i][j]<INFI?path[i][j]:"INF"));
			}
			System.out.println("");
		}
		System.out.println("");
		for (int i = 0; i < numVertices; i++){
			for (int j = 0; j < numVertices; j++){
				System.out.println("El costo minimo de "+vert.get(i).hashStr()+" a "+vert.get(j).hashStr()+" es "+(path[i][j]<INFI?path[i][j]:"INF"));
			}
		}
		System.out.println("");
		vert.clear();
	}
}
