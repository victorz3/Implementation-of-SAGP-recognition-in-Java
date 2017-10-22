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
import java.util.ArrayList;
import java.util.Stack;

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
    private List<List<Par>> sagp; /* Lista donde guardamos los SAGP (cada posición tiene una lista de pares; cada par tiene las posiciones inicial y final del SAGP)*/
    private int[] findR; /* El arreglo FindR de la cadena. */
    
    /**
     * Constructor.
     * @param texto - El texto sobre el que queremos encontrar SAGP.
     */
    public SAGP(String texto){
	this.t = texto;
	this.sagp = new ArrayList<>(texto.length());
	while(sagp.size() < texto.length()) sagp.add(null); /* Incrementamos el tamaño de nuestra lista con nulos para poder usar insert. */
	/* Algoritmo para calcular SAGP(T): */
	pals = StringUtil.pals(t);
	char c; /* El carácter actual */
	nextPos = new Integer[texto.length()]; /* Arreglo con la siguiente posición en la que aparece el carácter de cada índice */
	int tamano = 256 < texto.length() ? 256 : texto.length(); /* Tamaño del alfabeto */
	lMost = new Hashtable<>(tamano); /* Diccionario con la posición más a la izquierda de cada carácter en el texto */
	tipo1 = new LinkedList<>();
	tipo2 = new LinkedList<>();
	/* Llenamos arreglos necesarios para el algoritmo */
	for(int i = texto.length()-1; i >= 0; --i){/* Solo vamos a clasificar pivotes entre 1 y length-1 */
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
	    List<Par> canonicos = new ArrayList<>(); /* Lista donde iremos guardando los SAGP maximales canónicos */
	    int max = 0; /* Valor de la máxima w encontrada */
	    int maxgap = 0; /* Valor de la brecha con w más grande */
	    for(int gap = 1; gap < posicion - pals[posicion]; gap++){
		int pos1 = reversed[2* t.length() - (posicion-pals[posicion]-gap-1)]; /* Posición de T[1..i − Pals[i] − G]^R en SAT */
		int pos2 = reversed[posicion + pals[posicion]]; /* Posición de T[i + Pals[i] + 1..n] en SAT */
		int prefijo = pos1 < pos2 ? crm.consulta(pos1, pos2-1) : crm.consulta(pos2, pos1-1);
		if(lcp[prefijo] > max){
		    canonicos = new ArrayList<>(); /* Encontramos un w más grande, entonces los SAGP que encontramos antes no nos sirven */
		    max = lcp[prefijo];
		    maxgap = gap;
		    canonicos.add(new Par(posicion - pals[posicion]- maxgap- max, posicion + pals[posicion] + max-1));
		}else if(lcp[prefijo] == max) /* Si encontramos otro maximal canónico del mismo tamaño, lo agregamos */
		    canonicos.add(new Par(posicion - pals[posicion]- gap- max, posicion + pals[posicion] + max-1));
	    }
	    /* Llenamos con el sagp maximal canónico encontrado */
	    sagp.set(posicion, canonicos); 
	}
    }

    /**
     * Regresa la lista de SAGP para una posición.
     * @param pos - La posición para la cual regresaremos los SAGP maximales canónicos.
     * @return La lisa con coordenadas de inicio y final de los SAGP maximales canónicos de la posición.
     */
    public List<Par> getSAGP(int pos){
	return this.sagp.get(pos);
    }

    /* Calcula el arreglo FindR de la cadena. */
    private void calculaFindR(){
	this.findR = new int[t.length()]; /* Inicializamos el arreglo */
	int minIn, minOut; /* minin y minout en el algoritmo */
	minIn = minOut = Integer.MAX_VALUE; 
	Map<Character, Integer> occ1, occ2; /* Diccionarios con la última y penúltima posición vista de cada carácter */
	int tamano = 256 < t.length() ? 256 : t.length(); /* Tamaño del alfabeto */
	occ1 = new Hashtable<>(tamano);
	occ2 = new Hashtable<>(tamano);
	Stack<Integer> pila = new Stack<>();
	Character c; /* El carácter actual */
	for(int i = t.length()-1; i >= 0; --i){
	    c = t.charAt(i);
	    if(occ1.get(c) != null)
		occ2.put(c, occ1.get(c));
	    else
		occ2.put(c, Integer.MAX_VALUE);
	    occ1.put(c, i+1);
	    minIn = occ2.get(c) < minIn ? occ2.get(c) : minIn; /* Asignamos a minIn el mínimo de minIn y occ2[c] */
	    pila.push(i);
	    while(!pila.empty() && lMost.get(t.charAt(pila.peek())) >= i)
		pila.pop();
	    if(pila.empty())
		minOut = Integer.MAX_VALUE;
	    else
		minOut = pila.peek()+1;
	    findR[i] = minIn < minOut ? minIn : minOut; 
	}	
    }

    /**
     * Regresa el arrelo FindR de la cadena.
     * @return El arreglo FindR de la cadena.
     */
    public int[] getFindR(){
	if(this.findR == null)
	    calculaFindR();
	return this.findR;
    }
}
