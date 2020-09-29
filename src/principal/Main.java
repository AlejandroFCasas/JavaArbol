package principal;

import java.util.Scanner;

import leer.Leer;
import modelo.Persona;
import estructuras.Grafo;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[]args){
	Grafo g = new Grafo();
	Persona p = new Persona("benji",3425);
	Persona p2 = new Persona("laionel",234);
	Persona p3 = new Persona("gby",444);
	
	g.insVertice(p);
	g.insVertice(p2);
	g.insVertice(p3);
	g.insArista(p.getNombre(),p2.getNombre(), 15);
	g.insArista(p2.getNombre(),p3.getNombre(), 5);
	g.insArista(p.getNombre(),p3.getNombre(), 24);
	g.insArista(p3.getNombre(),p.getNombre(), 3);

	g.imprimirGrafo();
	g.mostrarCaminosMinimos();
	g.mostrarDistOrigen(p2.getNombre());
	g.mostrarCaminosMinConCostos();
	}
}
