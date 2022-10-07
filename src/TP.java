public class TP {
    private int[] array = new int[64];

    private RAM ram;

    public TP(int numMarcos, RAM ram) {
        this.ram = ram;
        for (int i = 0; i < array.length; i++) {
            if (i < numMarcos) {
                array[i] = i;
            } else {
                array[i] = -1;
            }
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
         * la TP" y se termina la ejecución del método.
         */
        if (array[referencia] != -1) {
            System.out.println("Referencia " + referencia + " encontrada en la TP");
            RAM.repeticionesMarcos[array[referencia]]++;
            return array[referencia];
        }

        /**
         * Si la referencia no está en la TP, se imprime el mensaje "Referencia no
         * encontrada en la TP" y se procede a buscarla en la RAM.
         */
        System.out.println("Referencia " + referencia + " no encontrada en la TP");
        int ubicacionFisica = ram.buscarReferencia(referencia);
        return ubicacionFisica;
    }
}
