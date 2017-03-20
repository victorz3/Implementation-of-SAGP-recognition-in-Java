import java.util.List;

/* Clase Nodo para representar nodos de árboles de sufijo */
public class Nodo{

    private Nodo suffixLink; /* Suffix Link */
    private List<Arista> aristas; /* Aristas que salen del Nodo */
    
    /* Pone el 'enlace de sufijo */
    public void setSuffixLink(Nodo link){
	this.suffixLink = link;
    }
}
