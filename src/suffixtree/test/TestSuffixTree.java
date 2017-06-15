package suffixtree.test;

import suffixtree.Ukkonen;
import suffixtree.SuffixTree;
import suffixtree.Arista;
import suffixtree.Nodo;
import util.MutableInt;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.util.List;


/* Clase para pruebas unitarias de SuffixTree */
public class TestSuffixTree{

    private SuffixTree banana; /* El árbol de sufijo de la palabra banana */
    
    /* Constructor.
     * Construímos manualmente el árbol de sufijo de 'banana'.
     */
    public TestSuffixTree(){
    }
    
    /* Prueba unitaria para subcadenas */
    @Test public void testSubcadenas(){
	Ukkonen u = new Ukkonen("banana");
	banana = u.ukkonen();
	List<String> l = banana.subcadenas(banana.getRaiz());
	Assert.assertTrue(l.size() == 7);
    }

    
}
