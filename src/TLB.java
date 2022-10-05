import java.util.HashMap;

public class TLB {

    /**
     * Número de entradas de a TLB.
     */
    int numEntradas;

    // TODO: Definir la estructura de la clase
    HashMap<String, Integer> tlb = new HashMap<String, Integer>();

    /**
     * Constructor de la clase TLB.
     * 
     * @param numEntradas Número de entradas de la TLB.
     */
    public TLB(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    /**
     * Método que devuelve el número de página asociado a una dirección virtual.
     * 
     * @param paginaVirtual
     * @return
     */
    public synchronized boolean estaEnTLB(String paginaVirtual) {
        return tlb.containsKey(paginaVirtual);
    }
}
