/* Clase de pruebas unitarias para la clase Nodo */
package suffixtree.test;

import suffixtree.Nodo;
import suffixtree.Arista;
import util.MutableInt;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


/**
 * Clase para pruebas unitarias de la clase {@link Nodo}.
 * @author Víctor Zamora Gutiérrez
 * @version 1.0 
 */
public class TestNodo{

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    private static Random rand = new Random();
    
    /**
     * Prueba unitaria para {@link Nodo#nuevaArista}.
     */
    @Test public void testNuevaArista(){
	Nodo n = new Nodo();
	List<Arista> listaTotal = new ArrayList<>();
	for(int i = 0; i < 10; ++i){
	    Arista a1 = new Arista((char)(i + 'a'), n, n, 0, new MutableInt(0));
	    listaTotal.add(a1);
	}
	Collections.sort(listaTotal);
	Assert.assertTrue(listaTotal.equals(n.getAristas()));
    }
}
