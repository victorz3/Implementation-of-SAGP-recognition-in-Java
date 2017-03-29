package suffixtree;

/* Representación de aristas */
public class Arista{

    private final Nodo desde,hasta; /* Los Nodos que conecta la arista */
    /* Inicio y fin de la subcadena que esta arista representa: */
    private final int inicio;
    private Integer fin; 

    /* Constructor estándar */
    public Arista(Nodo desde, Nodo hasta, int inicio, Integer fin){
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
    public Integer getFin(){
	return this.fin;
    }

    /* Cambia el punto de terminación de la Arista */
    public void setFin(Integer fin){
	this.fin = fin;
    }
    

    /* Regresa la longitud de la Arista */
    public int longitud(){
	return (fin-inicio) + 1;
    }

    /* Regresa la subcadena representada por la Arista */
    public String subcadena(String s){
	return s.substring(inicio, fin+1);
    }

}
