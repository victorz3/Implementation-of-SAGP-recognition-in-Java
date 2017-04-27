package util;

import java.util.Comparator;

/**
 * Clase que compara cadenas por longitud.
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class ComparadorLongitud implements Comparator<String>{

    /**
     * Compara dos cadenas por longitud.
     * @param s1 - La primera cadena a comparar
     * @param s2 - La segunda cadena a comparar
     * @return 1 si s1 es más grande que s2, 0 si tienen la misma longitud, -1 en otro caso
     */
    public int compare(String s1, String s2){
	return Integer.compare(s1.length(), s2.length()); // Magia
    }

    
    
}
