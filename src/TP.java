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
        Tiempo.sumarTraduccionDeDirecciones(30);

        /**
         * Si la pagina está en la TP, se imprime el mensaje "pagina encontrada
         * en
         * la TP" y se retorna el marco de página.
         */
        if (array[pagina] != -1) {
            ram.fueReferenciado(array[pagina]);
            Tiempo.sumarCargaDeDatos(30);
            return array[pagina]; // Se retorna el marco de pagina correspondiente
        }

        /**
         * No está la referencia al marco en la TP
         * Ocurre un fallo de página, retorna el marco de página asignado
         * Para arreglar el fallo de página en la TP se suma otra consulta en la TP
         * 30ns para arreglar el fallo en TP
         */
        else {

            int marcoPaginaNuevo = ram.agregarReferenciaVirtual(pagina);

            Tiempo.sumarTraduccionDeDirecciones(30);
            Tiempo.sumarCargaDeDatos(10000000);
            Tiempo.sumarCargaDeDatos(30);
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
