/* Clase que implementa el algoritmo de Ukkonen */
package suffixtree;

public class Ukkonen{
    private final String s; /* La cadena para la cuál construímos el árbol */
    /* Las siguientes variables son variables especiales del algoritmo 
       de Ukkonen */
    private Arista activeEdge = null;
    private int activeLength = 0;
    private int restantes = 1; /* Sufijos por insertar en la ronda 
				  actual del algoritmo de Ukkonen */
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

    /* Parte la arista activa e inserta un sufijo. */
    /* Indice final representa el último índice leído */
    public void split(Integer indiceFinal){
	/* Vamos a insertar dos aristas, por lo que necesitamos dos nodos: */
	Nodo split1 = new Nodo();
	Nodo split2 = new Nodo();
       	activeEdge.setFin(new Integer(activeLength));
	/* Como dije, creamos dos aristas: */
	Arista nueva1 = new Arista(activeEdge.getHasta(), split1, activeLength+1, indiceFinal);
	Arista nueva2 = new Arista(activeEdge.getHasta(), split2, indiceFinal.intValue(), indiceFinal);
    }
    
    /* Construye el árbol de sufijo para la cadena s */
    public SuffixTree ukkonen(String s){
	char actual; /* Carácter leído en el paso actual */
	Integer i; /* Contador. 
		    * Uso Integer para que cada que lo incremente, se incremente en las Aristas */
     	for(i = 0; i < s.length(); ++i){
	    actual = s.charAt(i); /* Leemos el siguiente carácter */
	    if(insertado(actual)){
		restantes++; /* El sufijo va a quedar pendiente */
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
		if(activeNode == root){
		    Nodo nuevo = new Nodo(); /* Creamos un nuevo Nodo que será el final de la Arista que insertemos */
		    Arista nueva = new Arista(root, nuevo, i.intValue(), i); /* La arista que insertamos */
		}else{ /* Se viene el caso complicado */
		    split(i);
		}
	    }
	    
	}
	return null;
    }
}
