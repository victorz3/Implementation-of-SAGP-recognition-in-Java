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
	Nodo raiz = new Nodo();
	Nodo a = new Nodo();
	Nodo na = new Nodo();
	Nodo hoja0 = new Nodo(); /* Hoja de 'banana#' */
	Nodo hoja1 = new Nodo(); /* Hoja de '#' */
	Arista ar_a = new Arista('a', raiz, a, 1, new MutableInt(1));
	Arista ar_banana$ = new Arista('b', raiz, hoja0, 0, new MutableInt(6));
	Arista ar_$ = new Arista('#', raiz, hoja1, 6, new MutableInt(6));
	Arista ar_na = new Arista('n', raiz, na, 2, new MutableInt(3));
	Nodo hoja2 = new Nodo(); /* Hoja de 'a#' */
	Nodo ana = new Nodo();
	Nodo hoja3 = new Nodo(); /* Hoja de 'na#' */
	Nodo hoja4 = new Nodo(); /* Hoja de 'nana#' */
	Arista ar_a$ = new Arista('a', a, hoja2, 6, new MutableInt(6));
	Arista ar_ana = new Arista('a', a, ana, 2, new MutableInt(3));
	Arista ar_na$ = new Arista('n', na, hoja3, 6, new MutableInt(6));
	Arista ar_nana$ = new Arista('n', na, hoja4, 4, new MutableInt(6));
	Nodo hoja5 = new Nodo(); /* Hoja de 'ana#' */
	Nodo hoja6 = new Nodo(); /* Hoja de 'anana#' */
	Arista ar_ana$ = new Arista('a', ana, hoja5, 6, new MutableInt(6));
	Arista ar_anana = new Arista('a', ana, hoja6, 4, new MutableInt(6));
	this.banana = new SuffixTree("banana#", raiz);
    }
    
    /* Prueba unitaria para subcadenas */
    @Test public void testSubcadenas(){
	List<String> l = banana.subcadenas(banana.getRaiz());
	Assert.assertTrue(l.size() == 7);
    }

    
}
