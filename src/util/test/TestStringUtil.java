package util.test;

import util.StringUtil; 
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


/**
 * Clase para pruebas unitarias de la clase {@link StringUtil}.
 */
public class TestStringUtil{

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link StringUtil#rellena}.
     */
    @Test public void testRellena(){
    }
    
}
