/* Clase que representa a un entero que se puede mutar */
package utileria;

public class MutableInt{
    private int value; /* Valor del entero */

    /* Crea un nuevo entero mutable */
    public MutableInt(int value){
	this.value = value;
    }
    
    /* Autoincremento */
    public void plusplus(){
	value++;
    }

    /* Regresa el valor del entero */
    public int getValue(){
	return value;
    }

    /* Nos dice si nuestro entero es mayor que i */
    public boolean mayor(int i){
	return value > i;
    }

    /* Nos dice si nuestro entero es menor que i */
    public boolean menor(int i){
	return value < i;
    }

    /* Nos dice si dos enteros mutables tienen el mismo valor */
    @Override
    public boolean equals(Object o){
	if(!(o instanceof MutableInt))
	    return false;
	MutableInt otro = (MutableInt) o;
	return otro.getValue() == value;
    }

    /* Devuelve una cadena con el número */
    @Override
    public String toString(){
	return Integer.toString(value);
    }
    
}
