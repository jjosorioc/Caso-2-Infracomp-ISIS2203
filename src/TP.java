public class TP {
    private int[] array = new int[64];

    private RAM ram;

    public TP(int numMarcos, RAM ram) {
        this.ram = ram;
        for (int i = 0; i < array.length; i++) {

            array[i] = -1; // -1 Indica que no está en la RAM

        }
    }

    /**
     * Busca la referencia en la TP.
     * 
     * @param referencia
     */
    public int buscarReferencia(Integer referencia) {

        /**
         * Si la referencia está en la TP, se imprime el mensaje "Referencia encontrada
         * en
         * la TP" y se retorna el marco de página.
         */
        if (array[referencia] != -1) {
            System.out.println("Referencia " + referencia + " encontrada en la TP");
            return array[referencia];
        }

        /**
         * Ocurre un fallo de página, retorna el marco de página asignado
         */
        else {
            System.out.println("Fallo de página con la referencia " + referencia);
            int marcoPaginaNuevo = ram.agregarReferenciaVirtual(referencia);

            /**
             * Se elimina el marco de página de la TP
             */
            for (int i = 0; i < array.length; i++) {
                if (array[i] == marcoPaginaNuevo) {
                    TLB.tlb.remove(i);
                    TLB.cola.remove(i);
                    array[i] = -1;
                    break;
                }
            }
            array[referencia] = marcoPaginaNuevo;

            return marcoPaginaNuevo;
        }
    }
}
