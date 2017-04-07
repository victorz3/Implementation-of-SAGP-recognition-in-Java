package suffixtree;

import utileria.MutableInt;

/* Representación de aristas */
public class Arista{

    private Nodo desde,hasta; /* Los Nodos que conecta la arista */
    /* Inicio y fin de la subcadena que esta arista representa: */
    private final int inicio;
    private MutableInt fin; /* Hacemos que el final se pueda mutar para poder "partir" aristas */ 
    
    /* Constructor estándar */
    public Arista(Nodo desde, Nodo hasta, int inicio, MutableInt fin){
	this.desde = desde;
	this.hasta = hasta;
	this.hasta.setPadre(this);
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
	return desde.equals(otra.getDesde()) && hasta.equals(otra.getHasta()) && inicio == otra.getInicio() && fin.equals(otra.getFin());
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
    public MutableInt getFin(){
	return this.fin;
    }

    /* Cambia el punto de terminación de la Arista */
    public void setFin(MutableInt fin){
	this.fin = fin;
    }

    /* Regresa la longitud de la Arista */
    public int longitud(){
	return (fin.getValue()-inicio) + 1;
    }

    /* Regresa la subcadena representada por la Arista */
    public String subcadena(String s){
	try{
	    return s.substring(inicio, fin.getValue()+1);
	}catch(StringIndexOutOfBoundsException e){
	    return s.substring(inicio, fin.getValue());
	}
    }
    
    /* Regresa el primer carácter de la Arista dentro de la cadena s. */
    public char getPrimero(String s){
	return s.charAt(inicio);
    }

    /* Establece el Nodo hasta el que llega la arista */
    public void setHasta(Nodo n){
	this.hasta = n;
	n.setPadre(this);
    }

}
