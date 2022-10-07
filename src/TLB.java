import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TLB {

    /**
     * Número de entradas de a TLB.
     */
    private int numEntradas;

    /**
     * Permite saber si una dirección virtual está en la TLB.
     */
    private HashSet<Integer> conjunto;

    /**
     * Cola para determinar qué dirección virtual debe ser reemplazada.
     */
    private Queue<Integer> cola;

    private TP tp;

    /**
     * Constructor de la clase TLB.
     * 
     * @param numEntradas Número de entradas de la TLB.
     */
    public TLB(int numEntradas, TP tp) {
        this.numEntradas = numEntradas;
        this.conjunto = new HashSet<>();
        this.cola = new LinkedList<>();
        this.tp = tp;
    }

}
