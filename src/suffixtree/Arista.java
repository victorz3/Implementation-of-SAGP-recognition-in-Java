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

    /* Compara dos Aristas */
    @Override
    public boolean equals(Object o){
	if(!(o instanceof Arista))
	    return false;
	Arista otra = (Arista) o; /* Necesario hacer un cast para comparar */
	return desde.equals(otra.getDesde()) && hasta.equals(otra.getHasta()) && inicio == otra.getInicio() && fin == otra.getFin();
    }

    /* Regresa el Nodo inicial de la Arista */
    public Nodo getDesde(){
	return this.desde;
    }

    /* Regresa el Nodo en el que termina la Arista */
    public Nodo getHasta(){
	return this.hasta;
    }
    
    /* Regresa el índice de inicio de la Arista */
    public int getInicio(){
	return this.inicio;
    }

    /* Regresa el índice final de la Arista */
    public int getFin(){
	return this.fin;
    }
    

    /* Regresa la longitud de la Arista */
    public int longitud(){
	return (fin-inicio) + 1;
    }
}
