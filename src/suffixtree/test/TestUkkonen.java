package suffixtree.test;

import suffixtree.Ukkonen;
import suffixtree.SuffixTree;
import suffixtree.Arista;
import suffixtree.Nodo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/* Clase para pruebas unitarias de Ukkonen */
public class TestUkkonen{

    /* Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    private static String banana = "banana#";
    private static Ukkonen u = new Ukkonen(banana);
    private static Nodo n1 = new Nodo();
    private static Arista a = new Arista(n1, n1, 0, 5);
    private static Nodo n2 = new Nodo();
    private static Arista b = new Arista(n1, n2, 1, 5);
    private static Nodo n3 = new Nodo();
    private static Arista c = new Arista(n1, n3, 3, 5);
    private static Nodo n4 = new Nodo();
    private static Arista d = new Arista(n1, n4, 2, 5);

    /* Establecemos el Nodo activo del algoritmo */
    public TestUkkonen(){
	u.setActiveNode(n1);
    }
    
    /* Prueba para startsWith */
    @Test public void testStartsWith(){
	Assert.assertTrue(u.startsWith('b', a));
	Assert.assertFalse(u.startsWith('a', a));
    }

    /* Prueba para get */
    @Test public void testGet(){
	Assert.assertTrue(u.get(3, a) == 'a');
	Assert.assertFalse(u.get(0, a) == 'a');
    }

    /* Prueba para insertado sin arista activa */
    @Test public void testInsertadoSinActiva(){
	Assert.assertTrue(u.insertado('a'));
	Assert.assertFalse(u.insertado('c'));
    }

    /* Prueba para insertado con arista activa */
    @Test public void testInsertadoConActiva(){
	Assert.assertTrue(u.insertado('n'));
	Assert.assertFalse(u.insertado('c'));
	u.setActiveEdge(b);
	u.setActiveLength(1);
    }

    /* Prueba para busca */
    @Test public void testBusca(){
	Assert.assertTrue(u.busca('w') == null);
	Assert.assertTrue(u.busca('n').equals(d));              
    }
    
}
