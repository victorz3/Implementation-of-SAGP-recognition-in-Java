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

    /**
     * Nos dice si dos pares son iguales.
     * @param o - El otro par a comparar
     * @return Si los pares son iguales o no.
     */
    @Override
    public boolean equals(Object o){
	if(!(o instanceof Par))
	    return false;
	Par otro = (Par) o; /* Hacemos cast a Par */
	return this.fst == otro.fst && this.snd == otro.snd;
    }

    /**
     * Regresa una cadena que representa al Par.
     * @return Cadena representativa del par.
     */
    @Override
    public String toString(){
	return "(" + fst + ", " + snd + ")";
    }
}
