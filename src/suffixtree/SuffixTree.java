package suffixtree;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import suffixtree.Ukkonen;
import util.ComparadorLongitud;

/* Clase para árboles de sufijo. 
 * A lo largo de este archivo, voy a suponer que '#' no está en el alfabeto.
 * '#' será utilizado para representar el final de una cadena. */
public class SuffixTree{

    private final String cadena; /* La cadena sobre la que se elaboró el árbol */
    private final Nodo raiz; /* La raíz del árbol */
    private List<Integer> suffixArray; /* El arreglo de sufijos de la cadena del árbol */
    private int[] reversed; /* El arreglo de sufijos invertido */
    private int[] lcp; /* Arreglo LCP */    
    
    /* Constructor estándar */
    public SuffixTree(String cadena, Nodo raiz){
	this.cadena = cadena;
	this.raiz = raiz;
    }

    /**
     * Regresa el árbol de sufijos dado por el algoritmo de Ukkonen.
     * @param s - La cadena sobre la que construiremos el árbol de sufijos.
     * @return El árbol de sufijos de la cadena s.
     */
    public static SuffixTree getSuffixTree(String s){
	Ukkonen u = new Ukkonen(s); /* Instancia para realizar el algoritmo de Ukkonen */ 
	return u.ukkonen();
    }

    /* Regresa la raíz del árbol */
    public Nodo getRaiz(){
	return this.raiz;
    }

    /**
     * Regresa el arreglo de sufijos si este ya fue calculado y si no, lo calcula.
     * Siempre debemos obtener el arreglo de sufijos utilizando este método para evitar hacer
     * operaciones de más.
     * @return El arreglo de sufijos perteneciente a la cadena del árbol.
     */
    public List<Integer> getSuffixArray(){
	if(this.suffixArray == null)
	    this.suffixArray = suffixArray(this.raiz);
	return this.suffixArray;
    }

    /**
     * Regresa el arreglo de sufijos invertido de la cadena.
     * @return El arreglo de sufijos invertido de la cadena del árbol. 
     */
    public int[] getReversedSuffixArray(){
	if(reversed == null)
	    reversed = reversedSuffixArray();
	return reversed; 
    }

    /**
     * Regresa el arreglo LCP de la cadena.
     * @return El arreglo LCP de la cadena del árbol.
     */
    public int[] getLCP(){
	if(lcp == null)
	    lcp = lcp();
	return lcp;
    }
    
    /* Regresa la lista de subcadenas a partir del nodo */
    public List<String> subcadenas(Nodo n){
	ArrayList<String> regreso = new ArrayList<>(); /* Lista a regresar */
	if(n.esHoja()){
	    regreso.add("");
	    return regreso;
	}
	for(Arista vecino: n.getAristas()){
	    Nodo sig = vecino.getHasta(); /* Siguiente Nodo a visitar */
	    for(String sub: this.subcadenas(sig))
		regreso.add(vecino.subcadena(this.cadena) + sub);
	}
	return regreso;
    }

    
    /* Saca el arreglo de sufijos del árbol.
     * Cada sufijo se representa con el número del carácter en el que empieza. */ 
    private List<Integer> suffixArray(Nodo n){
	ArrayList<Integer> regreso = new ArrayList<>(); /* Lista a regresar */
	if(n.esHoja()){
	    regreso.add(cadena.length()+1);
	    return regreso;
	}
	for(Arista vecino: n.getAristas()){
	    Nodo sig = vecino.getHasta(); /* Siguiente Nodo a visitar */
	    for(Integer sub: this.suffixArray(sig))
		regreso.add(sub - vecino.subcadena(this.cadena).length());
	}
	return regreso;
    }

    /* Saca el arreglo de sufijos invertido de la cadena del árbol */
    private int[] reversedSuffixArray(){
	List<Integer> sA = this.getSuffixArray(); /* Obtenemos el arreglo de 
							* sufijos utilizando el singleton */
	int[] rev = new int[sA.size()]; /* El arreglo que vamos a regresar */
	for(int i = 0; i < sA.size(); ++i){
	    rev[sA.get(i)-1] = i+1;
	}
	return rev;
    }

    
    /* Saca el arreglo LCP */
    private int[] lcp(){
	List<Integer> sa = this.getSuffixArray(); /* Arreglo de sufijos. */
	int[] rev = this.getReversedSuffixArray(); /* Arreglo de sufijos invertido */
	int longest = 0; /* El lcp de los sufijos que están siendo analizados */
	int[] arreglolcp = new int[sa.size()]; /* El arreglo que vamos a regresa. */
	arreglolcp[0] = -1; /* La primera posición vale -1 pues el sufijo no 
			     * tiene predecesor lexicográfico */
	for(int i = 0; i < cadena.length()-1; i++){
	    int pos = rev[i] - 1; /* Posición en SA del sufijo que empieza en el 
				   * i-ésimo carácter. */ 
	    int pred = sa.get(pos - 1); /* Predecesor lexicográfico del sufijo. */
	    while(i+longest < cadena.length() && (pred - 1)+longest < cadena.length() &&
		  cadena.charAt(i+longest) == cadena.charAt((pred-1)+longest))
		longest++; 
	    arreglolcp[pos] = longest;
	    if(longest > 0)
		longest--;
	} 
	return arreglolcp;
    }
    
    /**
     * Imprime los sufijos del árbol en orden lexicográfico.
     */
    public void printSufijos(){
	List<String> l = subcadenas(this.raiz);
	for(String s: l)
	    System.out.print(s + ", ");
	System.out.println();
    }
    
}
