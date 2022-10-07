public class RAM {

    private int[] marcos;

    public RAM(int numMarcos) {
        this.marcos = new int[numMarcos];

        /**
         * Inicializamos los marcos de 0 ... numMarcos - 1.
         */
        for (int i = 0; i < numMarcos; i++) {
            this.marcos[i] = i;
        }
    }
}
