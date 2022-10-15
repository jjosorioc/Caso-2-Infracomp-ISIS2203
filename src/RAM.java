
public class RAM {

    /**
     * Arreglo que contiene la dirección virtual y la dirección física
     */
    private int[] marcosDirVirtual;

    /**
     * Arreglo que indica si un marco fue referenciado. Este se utiliza para saber
     * qué marco liberar.
     */
    private int[] referencias;

    /**
     * Constructor
     * 
     * @param numMarcos
     */
    public RAM(int numMarcos) {
        this.marcosDirVirtual = new int[numMarcos];
        this.referencias = new int[numMarcos];

        for (int i = 0; i < marcosDirVirtual.length; i++) {
            marcosDirVirtual[i] = -1;
        }
    }

    public synchronized int agregarReferenciaVirtual(Integer referenciaVirtual) {

        int marcoDePaginaDisponible = this.ramEstaLlena();
        if (marcoDePaginaDisponible != -1) {
            marcosDirVirtual[marcoDePaginaDisponible] = referenciaVirtual;
            this.fueReferenciado(marcoDePaginaDisponible);
            return marcoDePaginaDisponible;
        } else { // Si no hay marcos de página disponibles, se debe liberar uno
            int marcoDePagina = this.obtenerMarcoDePagina();
            marcosDirVirtual[marcoDePagina] = referenciaVirtual;
            this.fueReferenciado(marcoDePagina);
            return marcoDePagina;
        }

    }

    private int obtenerMarcoDePagina() {
        int valorMasPequeno = Integer.MAX_VALUE;
        int marcoDePaginaRespectivo = -1;

        for (int i = 0; i < referencias.length; i++) {
            if (referencias[i] < valorMasPequeno) {
                valorMasPequeno = referencias[i];
                marcoDePaginaRespectivo = i;
            }
        }

        referencias[marcoDePaginaRespectivo] = 0;
        return marcoDePaginaRespectivo;
    }

    /**
     * Retorna el primer marco de página disponile en la RAM, -1 si no hay.
     * 
     * @return
     */
    private int ramEstaLlena() {
        for (int i = 0; i < marcosDirVirtual.length; i++) {
            if (marcosDirVirtual[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public synchronized void envejecimiento() {

        for (int i = 0; i < referencias.length; i++) {

            referencias[i] = referencias[i] >> 1;
        }

    }

    /**
     * Indica que un marco ha sido referenciado
     * 
     * @param marco
     */
    public void fueReferenciado(Integer marco) {
        referencias[marco] = referencias[marco] | (int) Math.pow(2, 30);
    }
}
