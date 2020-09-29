package leer;
import java.util.Scanner;

public class Leer {

	public static int leerInt (Scanner sc){
		int a = 0;
		try{
			a = Validar.validarInt (sc.nextLine());
		}
		catch (NumberFormatException e){
			System.out.print ("No ingreso un numero valido, reingrese: ");
			a = leerInt(sc);
		}
		return a;
	}
	public static double leerDouble (Scanner sc){
		double a = 0;
		try{
			a = Validar.validarDouble (sc.nextLine());
		}
		catch (NumberFormatException e){
			System.out.print ("No ingreso un numero valido, reingrese: ");
			a = leerDouble(sc);
		}
		return a;
	}
	public static int leerInt (Scanner sc,int ini,int fin){
		int a = 0;
		try{
			a = Validar.validarInt (sc.nextLine());
			if (a < ini || a > fin){
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException e){
			System.out.print ("No ingreso un numero en el rango posible, reingrese: ");
			a = leerInt(sc,ini,fin);
		}
		return a;
	}
	public static String leerString (Scanner sc){
		String s=sc.nextLine();
		s = Validar.validarString(s);
		if (s==null){
			System.out.println ("No todos los caracteres ingresados son letras, reingrese: ");
			s = Leer.leerString(sc);
		}
		return s;
	}
}
