/* Clase para calcular los SAGP de una cadena */

package sagp;

import util.StringUtil;
import util.Par;
import util.CRM;
import suffixtree.SuffixTree;
import java.util.Map;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * Clase para calcular los SAGP de una cadena.
 * @author Víctor Zamora Gutiérrez
 */
public class SAGP{

    private String t; /* Texto sobre el que calcularemos SAGP */
    private int[] pals; /* Arreglo pals de la cadena. */
    private Integer[] nextPos; /* Arreglo con la siguiente posición en la que aparece el carácter de cada índice */
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
	nextPos = new Integer[texto.length()]; /* Arreglo con la siguiente posición en la que aparece el carácter de cada índice */
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
	for(int i = 1; i < texto.length(); ++i){
	    if(pals[i] > 0 && texto.length() > i + pals[i] && lMost.get(texto.charAt(i + pals[i])) < i - pals[i])
		tipo1.add(i);
	    else
		tipo2.add(i);
	}
    }

    /** 
     * Regresa la lista de posiciones de Tipo 1.
     * @return La lista de posiciones de Tipo 1.
     */
    public List<Integer> getTipo1(){
	return this.tipo1;
    }

    /**
     * Regresa el diccionario con las posiciones más a la izquierda de cada carácter.
     * @return El diccionario con las posiciones más a la izquierda de cada carácter.
     */
    public Map<Character, Integer> getLMost(){
	return this.lMost;
    }

    /**
     * Regresa el arreglo con la siguiente posición del carácter en la posición 'i'
     * @return El arreglo con la siguiente posición del carácter en la posición 'i'
     */
    public Integer[] getNextPos(){
	return this.nextPos;
    }
    
    /**
     * Método ingenuo para calcular SAGP1(T).
     */
    public void naiveSAGP1(){
	StringBuilder r = new StringBuilder(t); /* Para sacar la reversa de t */
	String reversa = r.reverse().toString(); /* Reversa del texto */
	String textoPrima = t + '$' + reversa; /* La T' del algoritmo */
	SuffixTree sT = SuffixTree.getSuffixTree(textoPrima); /* Árbol de sufijos de la cadena T' */
	List<Integer> suffixArray = sT.getSuffixArray(); /* Arreglo de sufijos de T' */
	int[] reversed = sT.getReversedSuffixArray(); /* Arreglo de sufijos invertido de T' */
	int[] lcp = sT.getLCP(); /* Arreglo LCP de T' */
	CRM crm = new CRM(lcp); /* Objeto para realizar consultas de rango mínimo sobre el arreglo lcp */
	for(Integer posicion: this.tipo1){
	    int max = 0; /* Valor de la máxima w encontrada */
	    int maxgap = 0; /* Valor de la brecha con w más grande */
	    for(int gap = 1; gap < posicion - pals[posicion]; gap++){
		int pos1 = reversed[2* t.length() - (posicion-pals[posicion]-gap-1)]; /* Posición de T[1..i − Pals[i] − G]^R en SAT */
		int pos2 = reversed[posicion + pals[posicion]]; /* Posición de T[i + Pals[i] + 1..n] en SAT */
		int prefijo = pos1 < pos2? crm.consulta(pos1, pos2-1) : crm.consulta(pos2, pos1-1);
		if(lcp[prefijo] > max){
		    max = lcp[prefijo];
		    maxgap = gap;
		}
	    }
	    /* Llenamos con el sagp maximal canónico encontrado */
	    sagp[posicion] = new Par(posicion - pals[posicion]- maxgap- max, posicion + pals[posicion] + max-1); 
	}
    }

    /**
     * Regresa el arreglo con los sagp de la cadena. 
     * @return El arreglo con coordenadas de inicio y final de los SAGP de la cadena.
     */
    public Par[] getSAGP(){
	return this.sagp;
    }
}
