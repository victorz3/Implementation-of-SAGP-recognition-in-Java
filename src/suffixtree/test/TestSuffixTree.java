package suffixtree.test;

import suffixtree.SuffixTree;
import suffixtree.Arista;
import suffixtree.Nodo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/* Clase para pruebas unitarias de SuffixTree */
public class TestSuffixTree{

    /* Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    private static String banana = "banana#";
    private static Nodo n1 = new Nodo();
    private static Arista a = new Arista(n1, n1, 0, 4);
    private static SuffixTree t = new SuffixTree(banana, n1);
	
    
    /* Prueba para startsWith */
    @Test public void testStartsWith(){
	Assert.assertTrue(t.startsWith('b', a));
	Assert.assertFalse(t.startsWith('a', a));
    }

    /* Prueba para get */
    @Test public void testGet(){
	Assert.assertTrue(t.get(3, a) == 'a');
	Assert.assertFalse(t.get(0, a) == 'a');
    }
    
}
