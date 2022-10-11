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
     * Busca la pagina en la TP.
     * 
     * @param pagina
     */
    public int buscarReferencia(Integer pagina) {

        /**
         * Si la pagina está en la TP, se imprime el mensaje "pagina encontrada
         * en
         * la TP" y se retorna el marco de página.
         */
        if (array[pagina] != -1) {
            ram.fueReferenciado(array[pagina]);
            return array[pagina]; // Se retorna el marco de pagina correspondiente
        }

        /**
         * Ocurre un fallo de página, retorna el marco de página asignado
         */
        else {

            int marcoPaginaNuevo = ram.agregarReferenciaVirtual(pagina);

            /**
             * Se elimina el marco de página de la TP
             */
            for (int i = 0; i < array.length; i++) {
                if (array[i] == marcoPaginaNuevo) {
                    array[i] = -1;
                    break;
                }
            }
            array[pagina] = marcoPaginaNuevo;

            return marcoPaginaNuevo;
        }
    }
}
