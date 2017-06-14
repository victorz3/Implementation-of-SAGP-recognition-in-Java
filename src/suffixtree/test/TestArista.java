/* Clase de pruebas unitarias para la clase Arista */
package suffixtree.test;

import suffixtree.Nodo;
import suffixtree.Arista;
import util.MutableInt;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Arista}.
 * @author Víctor Zamora Gutiérrez
 * @version 1.0 
 */
public class TestArista{

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link Arista#compareTo}.
     */
    @Test public void testCompareTo(){
	Arista a1 = new Arista('a', new Nodo(), new Nodo(), 0, new MutableInt(0));
	Arista a2 = new Arista('b', new Nodo(), new Nodo(), 0, new MutableInt(0));
	Assert.assertTrue(a2.compareTo(a1) > 0);
	Arista a3 = new Arista('#', new Nodo(), new Nodo(), 0, new MutableInt(0));
	Assert.assertTrue(a3.compareTo(a1) < 0);
	Assert.assertTrue(a2.compareTo(a3) > 0);
	Assert.assertTrue(a3.compareTo(a3) == 0);	
    }
    
}
