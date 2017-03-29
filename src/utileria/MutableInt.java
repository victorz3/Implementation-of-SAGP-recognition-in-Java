/* Clase que representa a un entero que se puede mutar */
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
    
}
