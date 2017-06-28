package suffixtree;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import util.ComparadorLongitud;

/* Clase para árboles de sufijo. 
 * A lo largo de este archivo, voy a suponer que '#' no está en el alfabeto.
 * '#' será utilizado para representar el final de una cadena. */
public class SuffixTree{

    private final String cadena; /* La cadena sobre la que se elaboró el árbol */
    private final Nodo raiz; /* La raíz del árbol */
    private List<Integer> suffixArray; /* El arreglo de sufijos de la cadena del árbol */
    private int[] reversed; /* El arreglo de sufijos invertido */

    
    /* Constructor estándar */
    public SuffixTree(String cadena, Nodo raiz){
	this.cadena = cadena;
	this.raiz = raiz;
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

    /**
     * Saca el arreglo de sufijos del árbol.
     * Cada sufijo se representa con el número del carácter en el que empieza.
     * @return El arreglo de sufijos correspondiente a la cadena del árbol. 
     */ 
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

    /**
     * Saca el arreglo de sufijos invertido de la cadena del árbol 
     * @return El arreglo de sufijos invertido de la cadena.
     */
    private int[] reversedSuffixArray(){
	List<Integer> sA = this.getSuffixArray(); /* Obtenemos el arreglo de 
							* sufijos utilizando el singleton */
	int[] rev = new int[sA.size()]; /* El arreglo que vamos a regresar */
	for(int i = 0; i < sA.size(); ++i)
	    rev[sA.get(i)-1] = i+1;
	return rev;
	
    }
    
   
    /* Rutina para marcar a todos los nodos como no visitados */
    private void desvisita(){
	desvisitaNodo(this.raiz);
    }

    /* Marca a un nodo como no visitado y recursa sobre sus vecinos */
    private void desvisitaNodo(Nodo n){
	n.visita(false);
	for(Arista vecino: n.getAristas())
	    desvisitaNodo(vecino.getHasta());
    }
    
    /**
     * Imprime los sufijos del árbol en orden lexicográfico.
     */
    public void printSufijos(){
	List<String> l = subcadenas(this.raiz);
	for(String s: l)
	    System.out.print(s + ", ");
	System.out.println();
	desvisita();
    }
    
}
