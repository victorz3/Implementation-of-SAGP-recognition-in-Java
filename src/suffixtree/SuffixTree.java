package suffixtree;

/* Clase para árboles de sufijo. 
 * A lo largo de este archivo, voy a suponer que '$' no está en el alfabeto.
 * '$' será utilizado para representar el final de una cadena. */
public class SuffixTree{

    private final String cadena; /* La cadena sobre la que se elaboró el árbol */
    private final Nodo raiz; /* La raíz del árbol */

    /* Constructor estándar */
    public SuffixTree(String cadena, Nodo raiz){
	this.cadena = cadena;
	this.raiz = raiz;
    }

    /* Imprime todos los sufijos de la cadena */
    public void printSufijos(){
	for(Arista vecino: raiz.getAristas()){
	    System.out.println(vecino.subcadena(this.cadena));
	}
    }
}
