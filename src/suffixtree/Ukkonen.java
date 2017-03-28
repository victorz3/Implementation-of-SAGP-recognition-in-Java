/* Clase que implementa el algoritmo de Ukkonen */
package suffixtree;

public class Ukkonen{
    private final String s; /* La cadena para la cuál construímos el árbol */
    /* Las siguientes variables son variables especiales del algoritmo 
       de Ukkonen */
    private Arista activeEdge = null;
    private int activeLength = 0;
    private int restantes = 0; /* Sufijos por insertar en la ronda actual del algoritmo de Ukkonen */
    private final Nodo root = new Nodo(); /* La raíz del árbol */
    private Nodo activeNode = root; /* El nodo activo debe inicializarse en la raíz */
    
    /* Creamos el objeto para ejecutar el algoritmo de Ukkonen con la 
     * cadena s */
    public Ukkonen(String s) throws IllegalArgumentException{
	/* Voy a revisar si la cadena contiene '#', el símbolo de terminación */
	if(!s.contains("#"))
	    this.s = s + "#";
	else
	    throw new IllegalArgumentException("La cadena no puede contener el carácter especial '#'");	    
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

    /* Parte una arista en 2 (inserta un sufijo en una arista) */
    public void split(Integer indice){
	int puntoPartida = activeEdge.getInicio()+(activeLength-1); /* Punto en el que partimos nuestra arista */
	activeEdge.setFin(new Integer(puntoPartida));
	/* Dos nuevos nodos en los que se divide la arista: */
	Nodo nuevo1 = new Nodo();
	Nodo nuevo2 = new Nodo();
	Arista nueva1 = new Arista(activeEdge.getHasta(), nuevo1, puntoPartida+1, indice);
	Arista nueva2 = new Arista(activeEdge.getHasta(), nuevo2, indice, indice);
    }
    
    /* Construye el árbol de sufijo para la cadena s */
    public SuffixTree ukkonen(String s){
	Nodo ultimoSplit; /* Último nodo sobre el que se hizo split */
	char actual; /* Carácter leído en el paso actual */
	Integer i; /* Contador. 
		    * Uso Integer para que cada que lo incremente, se incremente en las Aristas */
	for(i = 0; i < s.length(); ++i){
	    boolean primeroInsertado = true; /* Nos dice si el sufijo fue el primero en insertarse en esta iteración */
	    actual = s.charAt(i); /* Leemos el siguiente carácter */
	    restantes++; /* Un sufijo más por insertar */
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
		    if(activeLength + 1 >= activeEdge.longitud()){
			activeNode = activeEdge.getHasta();
			activeEdge = null;
			activeLength = 0;
		    }else /* No nos salimos de la arista */
			activeLength++;
		}
	    }else{ /* El carácter no se ha insertado */
		/* Lo insertamos */
		while(restantes > 0){
		    if(activeEdge == null){ /* El caso de insertar en una arista completamente nueva */
			Nodo nuevo = new Nodo(); /* Extremo de la nueva arista */
			Arista nueva = new Arista(activeNode, nuevo, i.intValue(), i);
			restantes--;
		    }else{ /* Partimos la arista */
			split(i);
			// Actualización de parámetros pendiente 
		    }
		}
	    }
	}
    return null;
    }
}
