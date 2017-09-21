package util.test;

import util.CRM; 
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


/**
 * Clase para pruebas unitarias de la clase {@link CRM}.
 */
public class TestCRM{

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);
    
    private static int[] prueba = {8, 7, 3, 20, 2, 17, 5, 21, 11, 12};
    private static int[] prueba2 = {-1, 0, 0, 4, 2, 3, 5, 8, 7, 6, 5, 4, 3, 2, 1, 2, 4, 7, 7, 6, 5, 4, 3, 2, 1, 2, 0, 1, 5, 3, 6, 7, 6, 5, 4, 3, 2, 1, 0, 1, 2, 1};
    private static CRM c = new CRM(prueba);
    private static CRM c2 = new CRM(prueba2);
        
    /**
     * Prueba unitaria para {@link CRM#getMin}.
     */
    @Test public void testGetMin(){
	Assert.assertTrue(c.getMin(3, 2) == 4);
	Assert.assertTrue(c.getMin(4, 3) == 4);
       int[] prueba3 = {-1, 0, 0, 4, 2, 3, 5, 8, 7, 6, 5, 4, 3, 2, 1, 2, 4, 7, 7, 6, 5, 4, 3, 2, 1, 2, 0, 1, 5, 3, 6, 7, 6, 5, 4, 3, 2, 1, 0, 1, 2, 1};
	CRM c3 = new CRM(prueba3);
    	Assert.assertTrue(c3.getMin(3, 2) == 4);
	Assert.assertTrue(c3.getMin(4, 3) == 4);
	
    }

    /**
     * Prueba unitaria para {@link CRM#consulta}
     */
    @Test public void testConsulta(){
	Assert.assertTrue(c.consulta(5, 9) == 6);
    }
}
