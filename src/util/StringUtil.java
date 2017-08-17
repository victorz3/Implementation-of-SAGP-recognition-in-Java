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

	/* Ya con la cadena rellena, podemos ejecutar el algoritmo. */
	int[] bigPals = new int[rellena.length()]; /* Creamos un arreglo para longitudes de palíndromos en s.
						      Al final vamos a tomar solo las longitudes de los palíndromos pares.*/
	int centro = 0, der = 0; /* Posición del centro y derecha del palíndromo sobre el que estamos parados */
	bigPals[0] = bigPals[bigPals.length-1] = 0; /* Las posiciones de los extremos se inicializan en 0. */
	for(int i = 1; i < bigPals.length - 1; ++i){
	    int espejo = 2*centro - i; /* Reflejamos i con respecto al centro del palíndromo actual. */
	    if(i < der)
		bigPals[i] = Math.min(der -i, bigPals[espejo]);
	    while(rellena.charAt(bigPals[i]+i+1) == rellena.charAt(i - 1 - bigPals[i])) /* Expandemos el palíndromo 
											   carácter a carácter. */
		bigPals[i]++;
	    if(i + bigPals[i] > der){ /* Actualizamos el palíndromo actual */
		centro = i;
		der = i + bigPals[i];
	    }
	}
	/* Falta hacer el arreglo de puros palíndromos pares. */
	int[] pals = new int[s.length()-1]; /* Arreglo de longitudes de palíndromos pares (el que vamos a regresar) */
	int j = 0; /* Contador para llenar el arreglo pals */
	for(int i = 3; i < bigPals.length - 2; i += 2)
	    pals[j++] = bigPals[i]/2; /* Dividimos entre dos porque queremos longitud de mitad */
	return pals;
    }

    /**
     * Rellena una cadena con caracteres especiales para ser utilizada en el algoritmo de Manacher. 
     * @param s - La cadena a rellenar
     * @return La cadena rellena con caracteres especiales
     */
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
