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

    /**
       * Prueba unitaria para {@link SAGP#naiveSAGP1}.
     */
    @Test public void testSimpleQuadraticSAGP1(){
	instancia2.simpleQuadraticSAGP1();
	List<Par> sagp = instancia2.getSAGP(3);
	List<Par> resultado = new ArrayList<>();
	resultado.add(new Par(0, 4));
	System.out.println(sagp);
	Assert.assertTrue(sagp.equals(resultado));
	instancia.simpleQuadraticSAGP1();
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


    /**
     * Prueba unitaria para {@link SAGP#getFindR}.
     */
    @Test public void testGetFindR(){
	int[] findR = instancia2.getFindR();
	int[] otro = {3, 3, 3, 4, 5, 6, 7, 8};
       	Assert.assertTrue(Arrays.equals(findR, otro));
	findR = instancia.getFindR();
	int[] otro2 = {3, 3, 3, 4, 5, 6, 7, 8, 9, 10, 12, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	Assert.assertTrue(Arrays.equals(findR, otro2));
    }

    /**
     * Prueba unitaria para {@link SAGP#SAGP2}.
     */
    @Test public void testGetSAGP2(){
	instancia.SAGP2();
	for(Integer posicion: instancia.getTipo2()){
	    if(posicion == 6)
		continue;
	    Assert.assertTrue(instancia.getSAGP(posicion).equals(new ArrayList<>()));
	}
	List<Par> resultado = new ArrayList<>();
	resultado.add(new Par(1, 9));
	Assert.assertTrue(instancia.getSAGP(6).equals(resultado));
	instancia2.SAGP2();
	for(Integer posicion: instancia2.getTipo2()){
	    if(posicion == 6)
		continue;
	    Assert.assertTrue(instancia2.getSAGP(posicion).equals(new ArrayList<>()));
	}
	resultado = new ArrayList<>();
	resultado.add(new Par(0, 7));
	Assert.assertTrue(instancia2.getSAGP(6).equals(resultado));
	SAGP instancia3 = new SAGP("aaaaaaabccbaae");
	instancia3.SAGP2();
	for(Integer posicion: instancia3.getTipo2()){
	    if(posicion == 3 || posicion == 4 || posicion == 5 || posicion == 9)
		continue;
	    Assert.assertTrue(instancia3.getSAGP(posicion).equals(new ArrayList<>()));
	}
	resultado = new ArrayList<>();
	resultado.add(new Par(0, 4));
	Assert.assertTrue(instancia3.getSAGP(3).equals(resultado));	
	resultado = new ArrayList<>();
	resultado.add(new Par(0, 6));
	Assert.assertTrue(instancia3.getSAGP(4).equals(resultado));
	resultado = new ArrayList<>();
	resultado.add(new Par(0, 6));
	resultado.add(new Par(1, 6));
	resultado.add(new Par(2, 6));
	Assert.assertTrue(instancia3.getSAGP(5).equals(resultado));
	resultado = new ArrayList<>();
	resultado.add(new Par(0, 12));
	resultado.add(new Par(1, 12));
	resultado.add(new Par(2, 12));	
	resultado.add(new Par(3, 12));
	resultado.add(new Par(4, 12));
	Assert.assertTrue(instancia3.getSAGP(9).equals(resultado));	
    }
}
