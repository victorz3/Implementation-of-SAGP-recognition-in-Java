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
import java.util.Arrays;


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

    /**
     * Prueba unitaria para {@link SuffixTree#getReversedSuffixArray}.
     */
    @Test public void testGetReversedSuffixArray(){
	Ukkonen u = new Ukkonen("banana");
	SuffixTree t = u.ukkonen();
	int[] rev = t.getReversedSuffixArray();
	int[] myrev = {5, 4, 7, 3, 6, 2, 1};
	Assert.assertTrue(Arrays.equals(rev, myrev));
    }

    /**
     * Prueba unitaria para {@link SuffixTree#getLCP}.
     */
    @Test public void testGetLCP(){
	Ukkonen u = new Ukkonen("abcabbca");
	SuffixTree t = u.ukkonen();
	int[] lcp = t.getLCP();
	int[] myLCP = {-1, 0, 1, 2, 0, 1, 3, 0, 2};
	Assert.assertTrue(Arrays.equals(lcp, myLCP));

	u = new Ukkonen("banana");
	t = u.ukkonen();
	lcp = t.getLCP();
	int[] myLCP2 = {-1, 0, 1, 3, 0, 0, 2};
	Assert.assertTrue(Arrays.equals(lcp, myLCP2));
    }
}
