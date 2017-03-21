package suffixtree;

/* Clase para árboles de sufijo. 
 * A lo largo de este archivo, voy a suponer que '$' no está en el alfabeto.
 * '$' será utilizado para representar el final de una cadena. */
public class SuffixTree{

    private final String cadena; /* La cadena sobre la que se elaboró el árbol */
    private final Nodo raiz; /* La raíz del árbol */

    /* Constructor estándar */
    public SuffixTree(String cadena, Nodo raiz){
	this.cadena = cadena;
	this.raiz = raiz;
    }

    /* Nos dice si la arista empieza con el carácter dado. */
    public boolean startsWith(char caracter, Arista a){
	return this.cadena.charAt(a.getInicio()) == caracter;
    }

    /* Regresa el carácter en la posición i, dentro de la arista */
    public char get(int i, Arista a){
	return this.cadena.charAt(i+a.getInicio());
    }

    /* Nos dice si el carácter ya fue insertado a partir del punto activo */
    public boolean insertado(char caracter, Nodo nodoActivo,
			     char aristaActiva, int puntoActivo){
	if(aristaActiva == '\0'){ /* No hay arista activa */
	    for(Arista vecino : nodoActivo.getAristas())
		if(startsWith(caracter, vecino))
		    return true;
	}else{
	    Arista actual = null; 
	    for(Arista vecino : nodoActivo.getAristas())
		if(startsWith(aristaActiva, vecino)){
		    actual = vecino;
		    break; /* Ya tenemos la arista en la que hay que 
			      buscar */
		}
	    if(get(puntoActivo, actual) == caracter)
		return true;
	}
	return false;
    }
	
    
    /* Construye el árbol de sufijo para la cadena s */
    public SuffixTree ukkonen(String s){
	char actual; /* Carácter leído en el paso actual*/
	
	/* Las siguientes variables son variables especiales del algoritmo de
	   Ukkonen */
	Nodo activeNode = this.raiz;
	char activeEdge = '\0';
	int activeLength = 0;

	int restantes = 1; /* Sufijos restantes a insertar en el paso 
			      actual */	
	Nodo raiz = new Nodo(); /* La raíz del árbol que construiremos */
	for(int i = 0; i < s.length(); ++i){
	    actual = s.charAt(i); /* Leemos el siguiente carácter */
	    if(insertado(actual, activeNode, activeEdge, activeLength)){
		/* Actualización de variables */
	    }else{
		//inserta(actual, activeNode, activeEdge, activeLength);
	    }
	    
	}
	return null;
    }
}
