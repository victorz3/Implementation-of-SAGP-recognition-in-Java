/* Clase Node para representar nodos de árboles de sufijo */
public class Node{

    private Node suffixLink; /* Suffix Link */
    private List<Arista> aristas; /* Aristas que salen del Nodo */
    
    /* Pone el 'enlace de sufijo */
    public void setSuffixLink(Node link){
	this.suffixLink = link;
    }
}
