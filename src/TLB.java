import java.util.HashMap;
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
    public static HashMap<Integer, Integer> tlb;

    /**
     * Cola para determinar qué dirección virtual debe ser reemplazada.
     */
    public static Queue<Integer> cola;

    private TP tp;

    /**
     * Constructor de la clase TLB.
     * 
     * @param numEntradas Número de entradas de la TLB.
     */
    public TLB(int numEntradas, TP tp) {
        this.numEntradas = numEntradas;
        TLB.tlb = new HashMap<>();
        TLB.cola = new LinkedList<>();
        this.tp = tp;
    }

    /**
     * Busca la referencia en la TLB.
     * 
     * @param referencia
     */
    public void buscarReferencia(Integer referencia) {
        /**
         * Si la referencia está en la TLB, se imprime el mensaje "Referencia encontrada
         * en
         * la TLB" y se termina la ejecución del método.
         */
        if (tlb.containsKey(referencia)) {

            System.out.println("Referencia " + referencia + " encontrada en la TLB");
            return;
        }

        /**
         * Si la referencia no está en la TLB, se imprime el mensaje "Referencia no
         * encontrada en la TLB" y se procede a buscarla en la TP.
         */
        System.out.println("Referencia " + referencia + " no encontrada en la TLB");
        Integer ubicacionFisica = tp.buscarReferencia(referencia);

        /**
         * Si la TLB no está llena, se agrega la referencia a la TLB.
         */
        if (tlb.size() < numEntradas) {
            tlb.put(referencia, ubicacionFisica);
            cola.add(referencia);
        } else {
            /**
             * Si la TLB está llena, se reemplaza la referencia más antigua por la nueva
             * referencia.
             */
            Integer referenciaAntigua = cola.poll();
            tlb.remove(referenciaAntigua);
            tlb.put(referencia, ubicacionFisica);
            cola.add(referencia);
        }
    }

}
