public class TP {
    private int[] array = new int[64];

    private RAM ram;
    private Tiempo tiempo;

    public TP(int numMarcos, RAM ram, Tiempo tiempo) {
        this.ram = ram;
        this.tiempo = tiempo;
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
        //60ns  
        else {
            System.out.println("Fallo de página con la referencia " + referencia);
            tiempo.agregarTiempo(60);
            System.out.println("Tiempo total (fallo y resolución): " + tiempo.getTotal());
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
