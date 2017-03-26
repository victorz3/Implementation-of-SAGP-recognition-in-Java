/* Clase que implementa el algoritmo de Ukkonen */
package suffixtree;

public class Ukkonen{
    private String s; /* La cadena para la cuál construímos el árbol */
    /* Las siguientes variables son variables especiales del algoritmo 
       de Ukkonen */
    private Nodo activeNode = new Nodo();
    private Arista activeEdge = null;
    private int activeLength = 0;
    private int restantes = 1; /* Sufijos por insertar en la ronda 
				  actual del algoritmo de Ukkonen */

    /* Creamos el objeto para ejecutar el algoritmo de Ukkonen con la 
     * cadena s */
    public Ukkonen(String s){
	this.s = s; 
    }
    
    /* Pone un nuevo nodo activo. 
     * Este método se usa principalmente para pruebas */
    public void setActiveNode(Nodo n){
	this.activeNode = n;
    }

    /* Pone una nueva arista activa. 
     * Este método se usa principalmente para pruebas */
    public void setActiveEdge(Arista a){
	this.activeEdge = a;
    }

    /* Pone una nueva longitud activa. 
     * Este método se usa principalmente para pruebas */
    public void setActiveLength(int i){
	this.activeLength = i;
    }    

    /* Nos dice si la arista empieza con el carácter dado. */
    public boolean startsWith(char caracter, Arista a){
	return this.s.charAt(a.getInicio()) == caracter;
    }

    /* Busca la arista que empieza con el carácter c a partir del 
       NodoActivo */
    public Arista busca(char c){
	for(Arista vecino : activeNode.getAristas())
	    if(startsWith(c, vecino))
		return vecino;
	return null;
    }

    /* Regresa el carácter en la posición i, dentro de la arista */
    public char get(int i, Arista a){
	return this.s.charAt(i+a.getInicio());
    }
    
    /* Nos dice si el carácter ya fue insertado a partir del punto 
     * activo. 
     * También actualiza la arista activa si esta no existe. */
    public boolean insertado(char caracter){
	if(activeEdge == null){ /* No hay arista activa */
	    for(Arista vecino : activeNode.getAristas()){
		if(startsWith(caracter, vecino))
		    return true;
	    }
	}else
	    if(get(activeLength, activeEdge) == caracter)
		return true;
	return false;
    }
    
    /* Construye el árbol de sufijo para la cadena s */
    public SuffixTree ukkonen(String s){
	char actual; /* Carácter leído en el paso actual */
	
	for(int i = 0; i < s.length(); ++i){
	    actual = s.charAt(i); /* Leemos el siguiente carácter */
	    if(insertado(actual)){
		if(activeEdge == null){ /* Si no hay arista activa, 
					   la creamos */
		    activeEdge = this.busca(actual); /* Buscamos la 
							arista que 
							empieza con el 
							carácter */
		    activeLength++;
		}else{
		    /* Checamos si ya nos salimos de la arista */
		    if(activeLength + 1 >= activeEdge.longitud())
			return null;
			//Actualizar cosas 
		}
	    }else{
		//inserta(actual, activeNode, activeEdge, activeLength);
	    }
	    
	}
	return null;
    }
}
