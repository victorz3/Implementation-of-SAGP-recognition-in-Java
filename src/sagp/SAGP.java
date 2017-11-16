/* Clase para calcular los SAGP de una cadena */

package sagp;

import util.StringUtil;
import util.Par;
import util.CRM;
import suffixtree.SuffixTree;
import java.util.Map;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Clase para calcular los SAGP de una cadena.
 * @author Víctor Zamora Gutiérrez
 */
public class SAGP{

    private String t; /* Texto sobre el que calcularemos SAGP */
    private int[] pals; /* Arreglo pals de la cadena. */
    private Integer[] nextPos; /* Arreglo con la siguiente posición en la que aparece el carácter de cada índice */
    private Map<Character, Integer> lMost; /* Diccionario con la posición más a la izquierda de cada carácter en el texto */
    private List<Integer> tipo1; /* Lista de posiciones de tipo 1. */
    private List<Integer> tipo2; /* Lista de posiciones de tipo 2. */
    private List<List<Par>> sagp; /* Lista donde guardamos los SAGP (cada posición tiene una lista de pares; cada par tiene las posiciones inicial y final del SAGP)*/
    private int[] findR; /* El arreglo FindR de la cadena. */
    
    /**
     * Constructor.
     * @param texto - El texto sobre el que queremos encontrar SAGP.
     */
    public SAGP(String texto){
	this.t = texto;
	this.sagp = new ArrayList<>(texto.length());
	while(sagp.size() < texto.length()) sagp.add(null); /* Incrementamos el tamaño de nuestra lista con nulos para poder usar insert. */
	/* Algoritmo para calcular SAGP(T): */
	pals = StringUtil.pals(t);
	char c; /* El carácter actual */
	nextPos = new Integer[texto.length()]; /* Arreglo con la siguiente posición en la que aparece el carácter de cada índice */
	int tamano = 256 < texto.length() ? 256 : texto.length(); /* Tamaño del alfabeto */
	lMost = new Hashtable<>(tamano); /* Diccionario con la posición más a la izquierda de cada carácter en el texto */
	tipo1 = new LinkedList<>();
	tipo2 = new LinkedList<>();
	/* Llenamos arreglos necesarios para el algoritmo */
	for(int i = texto.length()-1; i >= 0; --i){/* Solo vamos a clasificar pivotes entre 1 y length-1 */
	    c = texto.charAt(i);
	    nextPos[i] = lMost.get(c);
	    lMost.put(c, i);
	}
	/* Clasificamos posiciones de acuerdo a su tipo (1 o 2). */
	for(int i = 1; i < texto.length(); ++i){
	    if(pals[i] > 0 && texto.length() > i + pals[i] && lMost.get(texto.charAt(i + pals[i])) < i - pals[i])
		tipo1.add(i);
	    else
		tipo2.add(i);
	}
    }

    /** 
     * Regresa la lista de posiciones de Tipo 1.
     * @return La lista de posiciones de Tipo 1.
     */
    public List<Integer> getTipo1(){
	return this.tipo1;
    }

    /** 
     * Regresa la lista de posiciones de Tipo 2.
     * @return La lista de posiciones de Tipo 2.
     */
    public List<Integer> getTipo2(){
	return this.tipo2;
    }

    
    /**
     * Regresa el diccionario con las posiciones más a la izquierda de cada carácter.
     * @return El diccionario con las posiciones más a la izquierda de cada carácter.
     */
    public Map<Character, Integer> getLMost(){
	return this.lMost;
    }

    /**
     * Regresa el arreglo con la siguiente posición del carácter en la posición 'i'
     * @return El arreglo con la siguiente posición del carácter en la posición 'i'
     */
    public Integer[] getNextPos(){
	return this.nextPos;
    }
    
    /**
     * Método ingenuo para calcular SAGP1(T).
     */
    public void naiveSAGP1(){
	StringBuilder r = new StringBuilder(t); /* Para sacar la reversa de t */
	String reversa = r.reverse().toString(); /* Reversa del texto */
	String textoPrima = t + '$' + reversa; /* La T' del algoritmo */
	SuffixTree sT = SuffixTree.getSuffixTree(textoPrima); /* Árbol de sufijos de la cadena T' */
	List<Integer> suffixArray = sT.getSuffixArray(); /* Arreglo de sufijos de T' */
	int[] reversed = sT.getReversedSuffixArray(); /* Arreglo de sufijos invertido de T' */
	int[] lcp = sT.getLCP(); /* Arreglo LCP de T' */
	CRM crm = new CRM(lcp); /* Objeto para realizar consultas de rango mínimo sobre el arreglo lcp */
	for(Integer posicion: this.tipo1){
	    List<Par> canonicos = new ArrayList<>(); /* Lista donde iremos guardando los SAGP maximales canónicos */
	    int max = 0; /* Valor de la máxima w encontrada */
	    int maxgap = 0; /* Valor de la brecha con w más grande */
	    for(int gap = 1; gap < posicion - pals[posicion]; gap++){
		int pos1 = reversed[2* t.length() - (posicion-pals[posicion]-gap-1)]; /* Posición de T[1..i − Pals[i] − G]^R en SAT */
		int pos2 = reversed[posicion + pals[posicion]]; /* Posición de T[i + Pals[i] + 1..n] en SAT */
		int prefijo = pos1 < pos2 ? crm.consulta(pos1, pos2-1) : crm.consulta(pos2, pos1-1);
		if(lcp[prefijo] > max){
		    canonicos = new ArrayList<>(); /* Encontramos un w más grande, entonces los SAGP que encontramos antes no nos sirven */
		    max = lcp[prefijo];
		    maxgap = gap;
		    canonicos.add(new Par(posicion - pals[posicion]- maxgap- max, posicion + pals[posicion] + max-1));
		}else if(lcp[prefijo] == max) /* Si encontramos otro maximal canónico del mismo tamaño, lo agregamos */
		    canonicos.add(new Par(posicion - pals[posicion]- gap- max, posicion + pals[posicion] + max-1));
	    }
	    /* Llenamos con el sagp maximal canónico encontrado */
	    sagp.set(posicion, canonicos); 
	}
    }

    /**
     * Método cuadrático simple para calcular SAGP1(T).
     * Debugging this is going to be a nightmare.
     */
    public void simpleQuadraticSAGP1(){
	StringBuilder r = new StringBuilder(t); /* Para sacar la reversa de t */
	String reversa = r.reverse().toString(); /* Reversa del texto */
	String textoPrima = t + '$' + reversa; /* La T' del algoritmo */
	SuffixTree sT = SuffixTree.getSuffixTree(textoPrima); /* Árbol de sufijos de la cadena T' */
	List<Integer> suffixArray = sT.getSuffixArray(); /* Arreglo de sufijos de T' */
	int[] reversed = sT.getReversedSuffixArray(); /* Arreglo de sufijos invertido de T' */
	int[] lcp = sT.getLCP(); /* Arreglo LCP de T' */
	CRM crm = new CRM(lcp); /* Objeto para realizar consultas de rango mínimo sobre el arreglo lcp */

	/* Iteramos sobre los sufijos */
	for(Integer posicion: this.tipo1){
	    int k = reversed[posicion + pals[posicion]] -1; /* Posición de T[i + Pals[i] + 1..n] en SAT */
	    int inicial = posicion - pals[posicion]; /* Posición inicial de uuR */

	    /* Ahora recorremos SA hacia atrás y hacia adelante */

	    /* Primero hacia atrás */
	    int p = -1; /* Incializamos la p del algoritmo con un valor de 'error'. */
	    for(int i = k-1; i >= 0; --i){
		int x = suffixArray.get(i)-1; /* El iésimo elemento del arreglo de sufijos */
		if(x > t.length() && op(x) < (inicial - 1)){
		    p = i;
		    break;
		}
	    }
	    int q = -1; /* Lo mismo que con p */
	    for(int i = k+1; i < suffixArray.size(); ++i){
		int x = suffixArray.get(i)-1; /* El iésimo elemento del arreglo de sufijos */
		if(x >= t.length() && op(x) < (inicial - 1)){
		    q = i;
		    break;
		}
	    }

	    /* Ya tenemos los valores de p y q */
	    int prefijop = -1; /* lcp(p, k) */
	    int prefijoq = -1; /* lcp(k, q) */
	    if(p != -1)
		prefijop = lcp[crm.consulta(p+1, k)];
	    if(q != -1)
		prefijoq = lcp[crm.consulta(k+1, q)];
	    
	    int w = prefijop > prefijoq ? prefijop : prefijoq; /* Longitud de la W en el SAGP */
	    int sp = -1; /* La mayor s < p que cumple LCP[s+1] < W (donde W es la longitud de prefijo) */
	    int sq = -1; /* La menor s > q que cumple LCP[s-1] < W (donde W es la longitud de prefijo) */
	    if(prefijop != -1 && prefijop >= prefijoq){
		for(int i = p-1; i >= 0; --i)
		    if(lcp[i+1] < w){
			sp = i;
			break;
		    }
	    }
	    if(prefijoq != -1 && prefijoq >= prefijop){
		for(int i = q + 1; i < suffixArray.size(); ++i)
		    if(lcp[i] < w){
			sq = i;
			break;
		    }
		if(sq == -1) /* Se puede dar el caso de que todos los sufijos de k en adelante tengan al menos W de prefijo en común. 
				Ojo: esto no ocurre para los sufijos de k hacia atrás pues al principio está el sufijo '#'*/
		    sq = suffixArray.size();
	    }
	    
	    /* Ahora, obtenemos los SAGP de tipo 1 */

	    /* Primero para el caso en el que lcp(p, k) > lcp(k, q) */
	    List<Par> canonicos = new ArrayList<>(); /* Lista de SAGP maximales canónicos */
	    if(sp != -1)
		for(int i = sp+1; i <= p; ++i){
		    int sufijo = suffixArray.get(i)-1; /* Sufijo a examinar */
		    if(sufijo > t.length() && op(sufijo) < (inicial - 1)) /* Hay un sagp */
			canonicos.add(new Par(op(sufijo) - w+1, posicion + pals[posicion] + w-1));
		}

	    /* Ahora para sq */
	    if(sq != -1)
		for(int i = sq-1; i >= q; --i){
		    int sufijo = suffixArray.get(i)-1; /* Sufijo a examinar */
		    if(sufijo > t.length()&& op(sufijo) < (inicial - 1)) /* Hay un sagp */
			canonicos.add(new Par(op(sufijo) - w+1, posicion + pals[posicion] + w-1));
		}
	    
	    /* Llenamos con el sagp maximal canónico encontrado */
	    sagp.set(posicion, canonicos); 
	}
    }
    
    /* Calcula la posición original de un índice mayor o igual que n+2 para el algoritmo cuadrático simple */
    private int op(int indice){
	return 2*t.length() - indice;
    }
      
    

    /**
     * Método que calcula SAGP2.*/
    public void SAGP2(){
	if(this.findR == null)
	    calculaFindR();
	for(Integer posicion: this.tipo2){ /* Iteramos sobre las posiciones de tipo 2 */
	    List<Par> canonicos = new ArrayList<>(); /* Lista donde iremos guardando los SAGP maximales canónicos */
	    Integer r; /* La r del algoritmo */
	    if(pals[posicion] == 0 || pals[posicion] == 1)
		r = Integer.MAX_VALUE;
	    else
		r = findR[posicion - pals[posicion]] < posicion ?
		    findR[posicion - pals[posicion]] : Integer.MAX_VALUE;
	    if(r < Integer.MAX_VALUE){
		Integer copia = r; /* Copia de r (se guarda para tener el extremo derecho del sagp */
		int gapsize = r - lMost.get(t.charAt(r-1)) -1; /* Tamaño de la brecha */
 		while(gapsize > 0){
		    canonicos.add(new Par(r-gapsize-1, posicion + posicion - copia)); /* Agregamos el sagp a la lista */
		    int izqSig = nextPos[r-gapsize-1]; /* Extremo izquierdo del siguiente sagp */
		    gapsize -= (izqSig-(r-gapsize-1)); /* Nueva longitud de brecha */
		}
	    }
	    sagp.set(posicion, canonicos);
	}
    }


    /**
     * Regresa la lista de SAGP para una posición.
     * @param pos - La posición para la cual regresaremos los SAGP maximales canónicos.
     * @return La lisa con coordenadas de inicio y final de los SAGP maximales canónicos de la posición.
     */
    public List<Par> getSAGP(int pos){
	return this.sagp.get(pos);
    }

    /* Calcula el arreglo FindR de la cadena. */
    private void calculaFindR(){
	this.findR = new int[t.length()]; /* Inicializamos el arreglo */
	int minIn, minOut; /* minin y minout en el algoritmo */
	minIn = minOut = Integer.MAX_VALUE; 
	Map<Character, Integer> occ1, occ2; /* Diccionarios con la última y penúltima posición vista de cada carácter */
	int tamano = 256 < t.length() ? 256 : t.length(); /* Tamaño del alfabeto */
	occ1 = new Hashtable<>(tamano);
	occ2 = new Hashtable<>(tamano);
	Stack<Integer> pila = new Stack<>();
	Character c; /* El carácter actual */
	for(int i = t.length()-1; i >= 0; --i){
	    c = t.charAt(i);
	    if(occ1.get(c) != null)
		occ2.put(c, occ1.get(c));
	    else
		occ2.put(c, Integer.MAX_VALUE);
	    occ1.put(c, i+1);
	    minIn = occ2.get(c) < minIn ? occ2.get(c) : minIn; /* Asignamos a minIn el mínimo de minIn y occ2[c] */
	    pila.push(i);
	    while(!pila.empty() && lMost.get(t.charAt(pila.peek())) >= i)
		pila.pop();
	    if(pila.empty())
		minOut = Integer.MAX_VALUE;
	    else
		minOut = pila.peek()+1;
	    findR[i] = minIn < minOut ? minIn : minOut; 
	}	
    }

    /**
     * Regresa el arrelo FindR de la cadena.
     * @return El arreglo FindR de la cadena.
     */
    public int[] getFindR(){
	if(this.findR == null)
	    calculaFindR();
	return this.findR;
    }
}
