package util;
/* Clase con funciones sobre cadenas */

/**
 * Clase auxiliar que contiene funciones sobre cadenas.
 * @author Victor Zamora
 * @version 1.0
 */
public class StringUtil{

    /**
     * Regresa el arreglo con la longitud de los palíndromos pares en la cadena s.
     * @param s - La cadena sobre la que sacaremos el arreglos de palíndromos.
     * @return El arreglo de palíndromos de la cadena s.
     */
    public static int[] pals(String s){
	String rellena = rellena(s); /* Rellenamos los espacios de la cadena con caracteres 
					especiales para poder ejecutar el algoritmo de Manacher. */	
	return new int[1];
    }

    /**
       Rellena una cadena con caracteres especiales para ser utilizada en el algoritmo de Manacher. */
    public static String rellena(String s) throws IllegalArgumentException{
	if(s.contains("$") || s.contains("@") || s.contains("¿"))
	    throw new IllegalArgumentException("La cadena no puede contener los caracteres $, @ y ¿");  
	String nueva = "$"; /* Inicializamos la nueva cadena con un carácter de inicio 
			       determinado por nosotros. 
			       Dicho carácter no debe estar contenido en s. */
	for(int i = 0; i < s.length(); ++i){
	    nueva += "@";
	    nueva += s.charAt(i);
	}
	nueva += "@¿"; /* Relleno del final */
	return nueva;
    }
     
}
