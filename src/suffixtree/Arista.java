package suffixtree;

/* Representación de aristas */
public class Arista{

    private Nodo desde,hasta; /* Los Nodos que conecta la arista */
    private Integer inicio,fin; /* Inicio y fin de la subcadena que esta arista 
			       representa */

    /* Constructor estándar */
    public Arista(Nodo desde, Nodo hasta, int inicio, int fin){
	this.desde = desde;
	this.hasta = hasta;
	this.inicio = inicio;
	this.fin = fin;
	desde.nuevaArista(this);
    }

    /* Regresa el índice de inicio de la Arista */
    public int getInicio(){
	return this.inicio;
    }

    /* Regresa la longitud de la Arista */
    public int longitud(){
	return (fin-inicio) + 1;
    }
}
