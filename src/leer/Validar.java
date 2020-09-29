package leer;

public class Validar {
		public static int validarInt(String s)
		{
			int a = 0;
			a = Integer.parseInt(s);
			return a;
		}
		public static double validarDouble(String s)
		{
			double a = 0;
			a = Double.parseDouble(s);
			return a;
		}
		public static String validarString (String s)
		{
			for (int i = 0; i < s.length(); i++){
				char c = s.charAt(i);
				if (!(('a'<= c && c <= 'z')||('A'<= c && c <= 'Z'))){
					return null;
				}
			}
			return s;
		}
	}
