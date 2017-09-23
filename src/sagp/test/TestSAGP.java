/* Clase de pruebas unitarias para la clase SAGP */
package sagp.test;

import sagp.SAGP;
import util.Par;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


/**
 * Clase para pruebas unitarias de la clase {@link SAGP}.
 * @author Víctor Zamora Gutiérrez
 * @version 1.0 
 */
public class TestSAGP{

    private static SAGP instancia = new SAGP("baaabaabaacbaabaabac");
    private static SAGP instancia2 = new SAGP("baaabaab");
    
    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    
    /**
     * Prueba unitaria para {@link SAGP#getTipo1}.
     */
    @Test public void testGetTipo1(){
	List<Integer> resultado = new LinkedList<>();
	resultado.add(3);
	resultado.add(13);
	resultado.add(16);
	Assert.assertTrue(instancia.getTipo1().equals(resultado));
	List<Integer> resultado2 = new LinkedList<>();
	resultado2.add(3);
	Assert.assertTrue(instancia2.getTipo1().equals(resultado2));
    }

    /**
     * Prueba unitaria para {@link SAGP#getLMost}.
     */
    @Test public void testGetLMost(){
	Map<Character, Integer> lMost = instancia.getLMost();
	Assert.assertTrue(lMost.get('a') == 1);
	Assert.assertTrue(lMost.get('b') == 0);
	Assert.assertTrue(lMost.get('c') == 10);
	Map<Character, Integer> lMost2 = instancia2.getLMost();
	Assert.assertTrue(lMost.get('b') == 0);
	Assert.assertTrue(lMost.get('a') == 1);
    }


    /**
     * Prueba unitaria para {@link SAGP#getNextPos}.
     */
    @Test public void testGetNextPos(){
	Integer[] resultado = {4, 2, 3, 5, 7, 6, 8, 11, 9, 12, 19, 14, 13, 15, 17, 16, 18, null, null, null};
	Assert.assertTrue(Arrays.equals(instancia.getNextPos(), resultado));
	Integer[] resultado2 = {4, 2, 3, 5, 7, 6, null, null};
	Assert.assertTrue(Arrays.equals(instancia2.getNextPos(), resultado2));
    }

    /**
     * Prueba unitaria para {@link SAGP#naiveSAGP1}.
     */
    @Test public void testNaiveSAGP1(){
	instancia2.naiveSAGP1();
	List<Par> sagp = instancia2.getSAGP(3);
	List<Par> resultado = new ArrayList<>();
	resultado.add(new Par(0, 4));
	Assert.assertTrue(sagp.equals(resultado));
	instancia.naiveSAGP1();
	sagp = instancia.getSAGP(3);
	resultado = new ArrayList<>();
	resultado.add(new Par(0, 4));
	Assert.assertTrue(sagp.equals(resultado));
	sagp = instancia.getSAGP(13);
	resultado = new ArrayList<>();
	resultado.add(new Par(6, 18));
	resultado.add(new Par(3, 18));
	Assert.assertTrue(sagp.equals(resultado));
	sagp = instancia.getSAGP(16);
	resultado = new ArrayList<>();
	resultado.add(new Par(10, 19));
	Assert.assertTrue(sagp.equals(resultado));
    }

}
