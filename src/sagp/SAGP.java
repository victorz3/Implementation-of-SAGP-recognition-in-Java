/* Clase para calcular los SAGP de una cadena */

package sagp;

import util.StringUtil;
import util.Par;
import java.util.Map;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;

/**
 * Clase para calcular los SAGP de una cadena.
 * @author Víctor Zamora Gutiérrez
 */
public class SAGP{

    private String t; /* Texto sobre el que calcularemos SAGP */
    private int[] pals; /* Arreglo pals de la cadena. */
    private int[] nextPos; /* Arreglo con la siguiente posición en la que aparece el carácter de cada índice */
    private Map<Character, Integer> lMost; /* Diccionario con la posición más a la izquierda de cada carácter en el texto */
    private List<Integer> tipo1; /* Lista de posiciones de tipo 1. */
    private List<Integer> tipo2; /* Lista de posiciones de tipo 2. */
    private Par[] sagp; /* Arreglo donde guardamos los SAGP (guardo un par con sus posiciones inicial y final)*/
    
    /**
     * Constructor.
     * @param text - El texto sobre el que queremos encontrar SAGP.
     */
    public SAGP(String texto){
	this.t = texto;
	this.sagp = new Par[texto.length()];

	/* Algoritmo para calcular SAGP(T): */
	pals = StringUtil.pals(t);
	char c; /* El carácter actual */
	nextPos = new int[texto.length()]; /* Arreglo con la siguiente posición en la que aparece el carácter de cada índice */
	int tamano = 256 < texto.length() ? 256 : texto.length(); /* Tamaño del alfabeto */
	lMost = new Hashtable<>(tamano); /* Diccionario con la posición más a la izquierda de cada carácter en el texto */
	tipo1 = new LinkedList<>();
	tipo2 = new LinkedList<>();
	/* Llenamos arreglos necesarios para el algoritmo */
	for(int i = texto.length()-1; i >= 0; --i){
	    c = texto.charAt(i);
	    nextPos[i] = lMost.get(c);
	    lMost.put(c, i);
	}
	/* Clasificamos posiciones de acuerdo a su tipo (1 o 2). */
	for(int i = 1; i <= texto.length(); ++i){
	    if(lMost.get(texto.charAt(i + pals[i])) < i - pals[i])
		tipo1.add(i);
	    else
		tipo2.add(i);
	}
    }

    /**
     * Método ingenuo para calcular SAGP1(T).
     */
    public void naiveSAGP1(){

    }
}
