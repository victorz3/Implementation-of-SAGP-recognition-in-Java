/* Clase de pruebas unitarias para la clase Arista */
package suffixtree.test;

import suffixtree.Nodo;
import suffixtree.Arista;
import suffixtree.Ukkonen;
import util.MutableInt;
import java.util.List;
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

    /**
     * Prueba unitaria para {@link Arista#subcadena}.
     */
    @Test public void testSubcadena(){
	Ukkonen u2 = new Ukkonen("abcabbca");
	Nodo raiz = new Nodo();
	u2.setActiveNode(raiz);
	Arista a1 = new Arista('a', raiz, new Nodo(), 0, new MutableInt(1)); //ab
	Arista a2 = new Arista('c', a1.getHasta(), new Nodo(), 2, new MutableInt(8)); //cabbca#
	Arista a3 = new Arista('b', a1.getHasta(), new Nodo(), 5, new MutableInt(8)); //bca#
	u2.setActiveEdge(a1);
	u2.setActiveLength(1);
	u2.split(new MutableInt(8));
	Assert.assertTrue(a1.getFin().getValue() == 0);
	List<Arista> vecinos = a1.getHasta().getAristas();
	Arista a6 = vecinos.get(0);
	Assert.assertTrue(a6.subcadena("abcabbca").equals("#"));
    }
	
    
}
