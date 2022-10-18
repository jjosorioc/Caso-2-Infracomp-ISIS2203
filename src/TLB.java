import java.util.HashMap;
import java.util.LinkedList;

public class TLB {

    /**
     * Número de entradas de a TLB.
     */
    private int numEntradas;

    /**
     * Permite saber si una dirección virtual está en la TLB.
     */
    private HashMap<Integer, Integer> tlb;

    /**
     * Cola para determinar qué dirección virtual debe ser reemplazada.
     */
    private LinkedList<Integer> cola;

    /**
     * Tabla de páginas
     */
    private TP tp;

    private RAM ram;

    /**
     * Constructor de la clase TLB.
     * 
     * @param numEntradas Número de entradas de la TLB.
     */
    public TLB(int numEntradas, TP tp, RAM ram) {
        this.numEntradas = numEntradas;
        this.tlb = new HashMap<>();
        this.cola = new LinkedList<>();
        this.tp = tp;
        this.ram = ram;
    }

    /**
     * Busca la referencia en la TLB.
     * 
     * @param referencia
     */
    public void buscarReferencia(Integer referencia) {
        /**
         * Se hace un recorrido en la TLB, su tiempo es de 2ns
         * Si la referencia está en la TLB, se imprime el mensaje "Referencia encontrada
         * en
         * la TLB" y se termina la ejecución del método.
         */
        if (tlb.containsKey(referencia)) {

            Tiempo.sumarTraduccionDeDirecciones(2);
            Tiempo.sumarCargaDeDatos(30);

            ram.fueReferenciado(tlb.get(referencia));
            return;
        } else // Buscar en la TP y agregar a la TLB
        {
            int marcoDePagina = tp.buscarReferencia(referencia);

            /**
             * Eliminar de la TLB
             */
            Integer paginaVieja = -1;
            for (Integer i : tlb.keySet()) {
                if (tlb.get(i) == marcoDePagina) {
                    tlb.remove(i);
                    paginaVieja = i;
                    break;
                }
            }

            /**
             * Eliminar de la cola
             */
            int index = 0;
            while (index < cola.size() && paginaVieja != -1) {
                if (cola.get(index) == paginaVieja) {
                    cola.remove(index);
                    break;
                }
                index++;
            }

            /**
             * Si la TLB no está llena, se agrega la nueva referencia.
             */
            if (tlb.size() < numEntradas) {
                tlb.put(referencia, marcoDePagina);
                cola.add(referencia);
            } else {
                /**
                 * Si la TLB está llena, se elimina la referencia más antigua y se agrega la
                 * nueva.
                 */
                int referenciaReemplazar = cola.poll();
                tlb.remove(referenciaReemplazar);
                tlb.put(referencia, marcoDePagina);
                cola.add(referencia);
            }
        }

    }

}
