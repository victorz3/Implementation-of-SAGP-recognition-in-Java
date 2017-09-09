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
    CRM c = new CRM(prueba);
    
    /**
     * Prueba unitaria para {@link CRM#getMin}.
     */
    @Test public void testGetMin(){
	Assert.assertTrue(c.getMin(3, 2) == 4);
	Assert.assertTrue(c.getMin(4, 3) == 4);
    }

    /**
     * Prueba unitaria para {@link CRM#consulta}
     */
    @Test public void testConsulta(){
	Assert.assertTrue(c.consulta(5, 9) == 6);
    }
}
