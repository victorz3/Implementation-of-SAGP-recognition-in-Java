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
	String relleno = rellena(s); /* Cadena rellenada */ 
	int[] pals = new int[s.length()];
	int centro = 0, der = 0; /* Posición del centro y derecha del palíndromo sobre el que estamos parados */
	for(int i = 1; i < s.length(); ++i){
	    int espejo = 2*centro - i; /* Reflejamos i con respecto al centro del palíndromo actual. */
	    if(i < der)
		pals[i] = Math.min(der -i, pals[espejo]);
	    while(relleno.charAt(pals[i]+i+1) == relleno.charAt(i - pals[i])) /* Expandemos el palíndromo 
										   carácter a carácter. */
		pals[i]++;
	    if(pals[i] > 0 && i + pals[i] - 1 > der){ /* Actualizamos el palíndromo actual */
		centro = i; 
		der = i + pals[i] -1;
	    }
	}
	return pals;
    }

    /**
     * Rellena una cadena con caracteres especiales para ser utilizada en el algoritmo de Manacher. 
     * @param s - La cadena a rellenar
     * @return La cadena rellena con caracteres especiales
     */
    public static String rellena(String s) throws IllegalArgumentException{
	if(s.contains("$") || s.contains("¿"))
	    throw new IllegalArgumentException("La cadena no puede contener los caracteres $, @ y ¿");  
	String nueva = "$"; /* Inicializamos la nueva cadena con un carácter de inicio 
			       determinado por nosotros. 
			       Dicho carácter no debe estar contenido en s. */
	nueva += s;
	nueva += "¿"; /* Relleno del final */
	return nueva;
    }     
}
