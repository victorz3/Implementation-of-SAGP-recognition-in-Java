/* Clase con que calcula consultas de rango mínimo en un arreglo */

package util;

import java.util.Arrays;

/**
 * Clase para realizar consultas de rango mínimo en un arreglo.
 * @author Víctor Zamora Gutiérrez.
 */
public class CRM{

    private int[] arreglo; /* Arreglo sobre el que realizaremos la consulta */ 
    private Integer[][] query; /* Arreglo sobre el que haremos las consultas */
    
    /**
     * Constructor que realiza el preprocesamiento para llevar a cabo consultas de rango mínimo. 
     * @param arreglo - El arreglo sobre el que realizaremos las consultas.
     */
    public CRM(int[] arreglo){
	this.arreglo = arreglo;
	this.query = new Integer[arreglo.length][(int)(Math.log(arreglo.length)/Math.log(2))+1];
    }
    
    /**
     * Devuelve el índice con el mínimo valor entre dos índices.
     * @param i - El extremo izquierdo del rango
     * @param d - El extremo derecho del rango
     * @return Regresa el índice con el mismo valor entre los dos parámetros
     */
    public int consulta(int i, int d){
	if(i == d)
	    return i;
	if(i > d)
	    return consulta(d, i);
	else{
	    int k = (int)(Math.log(d-i)/Math.log(2)); /* Tamaño de bloque a revisar */
	    int indice1 = getMin(i, k); /* Índice del bloque izquierdo */
	    int indice2 = getMin((int)(d-Math.pow(2, k)+1), k); /* Índice del bloque derecho */
	    int valor1 = arreglo[indice1]; /* Valor del bloque izquierdo */
	    int valor2 = arreglo[indice2]; /* Valor del bloque derecho */
	    return valor1 < valor2 ? indice1 : indice2;
	}
    }

    /**
     * Devuelve el índice del mínimo elemento en el intervalo [i, i + 2^k -1], 
     * dentro de nuestro arreglo.
     * @param i - Índice en el que inicia el intervalo.
     * @param k - Logaritmo del tamaño del intervalo.
     * @return El índice del mínimo elemento en el intervalo [i, i + 2^k -1].
     */
    public int getMin(int i, int k){
	if(query[i][k] == null){
	    if(k == 0)
		query[i][k] = i;
	    else{
		int indice1 = getMin(i, k-1); /* Índice del mínimo del lado izquierdo */
		int potencia = (int)Math.pow(2, k-1); /* 2^k-1 */
		int indice2 = i + potencia >= arreglo.length ? indice1 : getMin(i+potencia, k-1); /* Índice del mínimo del lado derecho */
      		query[i][k] = arreglo[indice1] <= arreglo[indice2] ? indice1 : indice2; 
	    }
	}
	return query[i][k];
    }
} 
