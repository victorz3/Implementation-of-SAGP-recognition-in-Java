package suffixtree;

import java.util.List;
import java.util.ArrayList;

/* Clase para árboles de sufijo. 
 * A lo largo de este archivo, voy a suponer que '#' no está en el alfabeto.
 * '#' será utilizado para representar el final de una cadena. */
public class SuffixTree{

    private final String cadena; /* La cadena sobre la que se elaboró el árbol */
    private final Nodo raiz; /* La raíz del árbol */

    /* Constructor estándar */
    public SuffixTree(String cadena, Nodo raiz){
	this.cadena = cadena;
	this.raiz = raiz;
    }

    /* Regresa la raíz del árbol */
    public Nodo getRaiz(){
	return this.raiz;
    }
    
    /* Regresa la lista de subcadenas a partir del nodo */
    public List<String> subcadenas(Nodo n){
	ArrayList<String> regreso = new ArrayList<>(); /* Lista a regresar */
	n.visita(true);
	if(n.esHoja()){
	    regreso.add("");
	    return regreso;
	}
	for(Arista vecino: n.getAristas()){
	    Nodo sig = vecino.getHasta(); /* Siguiente Nodo a visitar */
	    if(sig.visitado())
		continue;
	    for(String sub: this.subcadenas(sig))
		regreso.add(vecino.subcadena(this.cadena) + sub);
	}
	return regreso;
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
    
    /* Imprime los sufijos del árbol */
    public void printSufijos(){
	List<String> l = subcadenas(this.raiz);
	for(String s: l)
	    System.out.print(s + ", ");
	System.out.println();
	desvisita();
    }
}
