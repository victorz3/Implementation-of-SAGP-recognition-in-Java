package suffixtree;

import java.util.List;
import java.util.ArrayList;

/* Clase Nodo para representar nodos de árboles de sufijo */
public class Nodo{

    private Nodo suffixLink = null; /* Suffix Link */
    private List<Arista> aristas = new ArrayList<>(); /* Aristas que salen del Nodo */        
    private boolean visitado = false; /* Nos dice si el nodo fue visitado en un recorrido */

    /* Nos dice si el nodo fue visitado durante un recorrido */
    public boolean visitado(){
	return this.visitado;
    }

    /* Marca al nodo como visitado o no visitado */
    public void visita(boolean b){
	this.visitado = b;
    }
    
    /* Pone el 'enlace de sufijo' */
    public void setSuffixLink(Nodo link){
	this.suffixLink = link;
    }

    /* Regresa el 'enlace de sufijo' */
    public Nodo getSuffixLink(){
	return suffixLink;
    }

    /* Agrega una arista que sale del Nodo */
    public void nuevaArista(Arista a){
	this.aristas.add(a);
    }

    /* Regresa la lista de aristas saliendo del Nodo */
    public List<Arista> getAristas(){
	return this.aristas;
    }

    /* Nos dice si el Nodo es hoja */
    public boolean esHoja(){
	return this.aristas.size() == 0;
    }
}
