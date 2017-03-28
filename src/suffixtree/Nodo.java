package suffixtree;

import java.util.List;
import java.util.ArrayList;

/* Clase Nodo para representar nodos de árboles de sufijo */
public class Nodo{

    private Nodo suffixLink; /* Suffix Link */
    private List<Arista> aristas = new ArrayList<>(); /* Aristas que salen del Nodo */        
    
    /* Pone el 'enlace de sufijo' */
    public void setSuffixLink(Nodo link){
	this.suffixLink = link;
    }

    /* Agrega una arista que sale del Nodo */
    public void nuevaArista(Arista a){
	this.aristas.add(a);
    }

    /* Regresa la lista de aristas saliendo del Nodo */
    public List<Arista> getAristas(){
	return this.aristas;
    }
}
