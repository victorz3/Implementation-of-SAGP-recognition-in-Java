/* Clase para guardar una pareja de números */

package util;

/**
 * Clase que representa un par de enteros.
 * @author Victor Zamora
 */
public class Par{

    private int fst; /* Primer elemento del par. */
    private int snd; /* Segundo elemento del par */

    /**
     * Construye un par a partir de sus entradas. 
     * @param fst - La primera entrada del par.
     * @param snd - La segunda entrada del par.
     */
    public Par(int fst, int snd){
	this.fst = fst;
	this.snd = snd;
    }

    /**
     * Regresa la primera entrada del par.
     * @return La primera entrada del par.
     */
    public int getFst(){
	return fst;
    }

    /**
     * Regresa la segunda entrada del par.
     * @return La segunda entrada del par.
     */
    public int getSnd(){
	return snd;
    }
}
