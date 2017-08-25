package util.test;

import util.StringUtil;
import java.util.Arrays;
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
	String nueva = StringUtil.rellena("abababa");
	Assert.assertTrue(nueva.equals("$abababa¿"));
    }

    /**
     * Prueba unitaria para {@link StringUtil#pals}.
     */
    @Test public void testPals(){
	String nueva = "abababa";
	int[] pals = StringUtil.pals(nueva);
	int[] resultado = {0, 0, 0, 0, 0, 0, 0};
	Assert.assertTrue(Arrays.equals(pals, resultado));
	nueva = "abaaba";
	pals = StringUtil.pals(nueva);
	int[] resultado2={0, 0, 0, 3, 0, 0};
	Assert.assertTrue(Arrays.equals(pals, resultado2));
	nueva = "ccabaabc";
	pals = StringUtil.pals(nueva);
	int[] resultado3 = {0, 1, 0, 0, 0, 2, 0, 0};
	Assert.assertTrue(Arrays.equals(pals, resultado3));
	nueva = "forgeeksskeegfor";
	pals = StringUtil.pals(nueva);
	int[] resultado4 = {0, 0, 0, 0, 0, 1, 0, 0, 5, 0, 0, 1, 0, 0, 0, 0};
	Assert.assertTrue(Arrays.equals(pals, resultado4));
    }
    
}
