package util.test;

import util.ComparadorLongitud; 
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


/**
 * Clase para pruebas unitarias de la clase {@link ComparadorLongitud}.
 */
public class TestComparadorLongitud{

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);
    
    private static String s1 = "Hola mundo";
    private static String vacia = "";
    private static String alfabeto = "abcdefghijklmnopqrstuvwxyz";
    private static ComparadorLongitud c = new ComparadorLongitud();
    
    /**
     * Prueba unitaria para {@link ComparadorLongitud#compare}.
     */
    @Test public void testCompare(){
	Assert.assertTrue(c.compare(vacia, alfabeto) < 0);
	Assert.assertTrue(c.compare(alfabeto, s1) > 0);
	Assert.assertTrue(c.compare(s1, s1) == 0);
    }
    
}
