public class RAM {

    public static int[] repeticionesMarcos;

    private int[] marcos;

    public RAM(int numMarcos) {
        this.marcos = new int[numMarcos];
        RAM.repeticionesMarcos = new int[numMarcos];

        /**
         * Inicializamos los marcos de 0 ... numMarcos - 1.
         */
        for (int i = 0; i < numMarcos; i++) {
            this.marcos[i] = i;
        }
    }

    /**
     * Busca la referencia en la RAM.
     * 
     * @param referencia
     */
    public int buscarReferencia(Integer referencia) {
        /**
         * Si la referencia está en la RAM, se imprime el mensaje "Referencia encontrada
         * en la RAM" y se termina la ejecución del método.
         */
        for (int i = 0; i < marcos.length; i++) {
            if (marcos[i] == referencia) {
                System.out.println("Referencia " + referencia + " encontrada en la RAM");
                return i;
            }
        }

        /**
         * Si la referencia no está en la RAM, se imprime el mensaje "Referencia no
         * encontrada en la RAM" y se procede a buscarla en el disco.
         */
        return -1;
    }
}
